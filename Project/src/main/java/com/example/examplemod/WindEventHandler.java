package com.example.examplemod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import com.example.examplemod.dimension.ModDimensions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WindEventHandler {

    private static final int MIN_EVENT_INTERVAL = 6000; // 5 minutes
    private static final int MAX_EVENT_INTERVAL = 12000; // 10 minutes
    private static final int WARNING_DURATION = 60; // 3 seconds warning
    private static final int WIND_BURST_COUNT = 8; // 8 bursts for 15 second event
    private static final int BURST_INTERVAL = 24; // 1.2 seconds between bursts

    private static final Map<UUID, WindEventData> playerWindData = new HashMap<>();
    private static int globalTickCounter = 0;
    private static boolean windEventsEnabled = true;

    private static class WindEventData {
        int nextEventTick;
        boolean isInWindEvent;
        int windEventStage;
        int windEventTimer;
        int burstsRemaining;

        WindEventData() {
            this.nextEventTick = calculateNextEventTime();
            this.isInWindEvent = false;
            this.windEventStage = 0;
            this.windEventTimer = 0;
            this.burstsRemaining = 0;
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (!windEventsEnabled) return;

        Player player = event.getEntity();
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        // Only affect players in the Illini dimension
        if (!player.level().dimension().equals(ModDimensions.ILLINI_DIM_KEY)) return;

        globalTickCounter++;
        UUID playerUUID = player.getUUID();

        // Initialize player data if needed
        playerWindData.computeIfAbsent(playerUUID, k -> new WindEventData());
        WindEventData data = playerWindData.get(playerUUID);

        // Check if it's time for a wind event
        if (!data.isInWindEvent && globalTickCounter >= data.nextEventTick) {
            startWindEvent(serverPlayer, data);
        }

        // Handle ongoing wind event
        if (data.isInWindEvent) {
            handleWindEvent(serverPlayer, data);
        }
    }

    private void startWindEvent(ServerPlayer player, WindEventData data) {
        data.isInWindEvent = true;
        data.windEventStage = 1;
        data.windEventTimer = 0;
        data.burstsRemaining = WIND_BURST_COUNT;

        player.displayClientMessage(Component.literal("§e⚠ §cSTRONG WINDS APPROACHING! §e⚠"), true);
        player.sendSystemMessage(Component.literal("§6⚡ The air feels heavy... A storm is brewing!"));

        player.level().playSound(null, player.blockPosition(),
            SoundEvents.ELYTRA_FLYING, SoundSource.WEATHER, 0.8f, 0.5f);

        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CLOUD,
                player.getX(), player.getY() + 5, player.getZ(),
                20, 3.0, 2.0, 3.0, 0.1);
        }
    }

    private void handleWindEvent(ServerPlayer player, WindEventData data) {
        data.windEventTimer++;

        if (data.windEventStage == 1) {
            if (data.windEventTimer % 10 == 0) {
                player.level().playSound(null, player.blockPosition(),
                    SoundEvents.ELYTRA_FLYING, SoundSource.WEATHER, 0.6f, 1.2f);
            }

            if (data.windEventTimer >= WARNING_DURATION) {
                data.windEventStage = 2;
                data.windEventTimer = 0;
                player.displayClientMessage(Component.literal("§4§l🌪 WIND STORM HITS! §4§l"), true);
            }
        }
        else if (data.windEventStage == 2) {
            if (data.windEventTimer % BURST_INTERVAL == 0 && data.burstsRemaining > 0) {
                executeWindBurst(player);
                data.burstsRemaining--;

                if (data.burstsRemaining <= 0) {
                    endWindEvent(player, data);
                }
            }
        }
    }

    private void executeWindBurst(ServerPlayer player) {
        double upwardForce = 1.2 + (Math.random() * 0.6);
        double horizontalForce = (Math.random() - 0.5) * 0.8;

        // Add wind effects (no damage, just effects)
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 80, 0, false, false));

        // Occasional stronger effects
        if (Math.random() < 0.4) {
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, false, false));
        }
        if (Math.random() < 0.2) {
            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 30, 0, false, false));
        }

        player.push(horizontalForce, upwardForce, horizontalForce);
        player.hurtMarked = false;
        sendVelocity(player);

        player.level().playSound(null, player.blockPosition(),
            SoundEvents.ELYTRA_FLYING, SoundSource.WEATHER, 1.0f, 1.0f);

        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.GUST,
                player.getX(), player.getY(), player.getZ(),
                15, 1.0, 0.5, 1.0, 0.3);
            serverLevel.sendParticles(ParticleTypes.CLOUD,
                player.getX(), player.getY() - 0.5, player.getZ(),
                8, 1.5, 0.3, 1.5, 0.1);
        }

        String[] messages = {
            "§f💨 A powerful gust lifts you into the air!",
            "§f🌪 The wind carries you upward!",
            "§f💨 You're swept up by the storm!"
        };
        player.displayClientMessage(
            Component.literal(messages[(int)(Math.random() * messages.length)]), true
        );
    }

    private void sendVelocity(ServerPlayer player) {
        // Send packet directly to client to force velocity sync
        player.connection.send(new ClientboundSetEntityMotionPacket(player));
    }

    private void endWindEvent(ServerPlayer player, WindEventData data) {
        data.isInWindEvent = false;
        data.windEventStage = 0;
        data.windEventTimer = 0;
        data.nextEventTick = globalTickCounter + calculateNextEventTime();

        // Event end notification
        player.sendSystemMessage(
            Component.literal("§2✓ The winds calm down... for now.")
        );

        // Gentle ending sound
        player.level().playSound(null, player.blockPosition(),
            SoundEvents.ELYTRA_FLYING, SoundSource.WEATHER, 0.4f, 0.7f);
    }

    private static int calculateNextEventTime() {
        return MIN_EVENT_INTERVAL + (int)(Math.random() * (MAX_EVENT_INTERVAL - MIN_EVENT_INTERVAL));
    }

    public static void triggerWindEventForPlayer(ServerPlayer player) {
        if (!player.level().dimension().equals(ModDimensions.ILLINI_DIM_KEY)) {
            player.sendSystemMessage(Component.literal("§cWind events can only be triggered in the Illini Dimension!"));
            return;
        }

        UUID playerUUID = player.getUUID();
        WindEventData data = playerWindData.computeIfAbsent(playerUUID, k -> new WindEventData());

        if (data.isInWindEvent) {
            player.sendSystemMessage(Component.literal("§cWind event already in progress!"));
            return;
        }

        WindEventHandler handler = new WindEventHandler();
        handler.startWindEvent(player, data);
        player.sendSystemMessage(Component.literal("§6⚡ Wind event manually triggered!"));
    }

    public static void setWindEventsEnabled(boolean enabled) {
        windEventsEnabled = enabled;
    }

    public static boolean areWindEventsEnabled() {
        return windEventsEnabled;
    }
}