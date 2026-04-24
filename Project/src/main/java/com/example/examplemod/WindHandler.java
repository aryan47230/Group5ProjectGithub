package com.example.examplemod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.telemetry.TelemetryProperty.GameMode;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class WindHandler {

    private int tickCounter = 0;
    private double windX = 0.9;
    private double windZ = 0.4;
    private int shiftTimer = 0;

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!(player instanceof ServerPlayer serverPlayer))
            return;
        if (!player.gameMode().isSurvival()) {
            return;
        }
        tickCounter++;
        shiftTimer++;

        if (shiftTimer >= 100) {
            shiftTimer = 0;
            windX = (Math.random() - 0.3) * 1.5;
            windZ = (Math.random() - 0.5) * 1.0;
        }

        if (tickCounter < 10)
            return;
        tickCounter = 0;

        double roll = Math.random();
        if (roll < 0.30) {
            normalWind(serverPlayer);
        } else if (roll < 0.65) {
            strongGust(serverPlayer);
        } else {
            violentGust(serverPlayer);
        }
    }

    private void sendVelocity(ServerPlayer player) {
        // Send packet directly to client to force velocity sync
        player.connection.send(new ClientboundSetEntityMotionPacket(player));
    }

    private void normalWind(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 0, false, true));
        player.hurt(player.damageSources().fall(), 0.5f);
        player.push(windX * 0.4, 0.05, windZ * 0.4);
        player.hurtMarked = false;
        sendVelocity(player);
        player.displayClientMessage(
                Component.literal("§e⚠ §bPlayer is being pushed by UIUC winds!"), true);
    }

    private void strongGust(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 80, 3, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 1, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 2, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20, 0, false, true));
        player.hurt(player.damageSources().fall(), 1.0f);
        player.push(
                windX * 1.0 + (Math.random() - 0.5) * 0.5,
                0.3,
                windZ * 1.0 + (Math.random() - 0.5) * 0.5);
        player.hurtMarked = false;
        sendVelocity(player);
        player.displayClientMessage(
                Component.literal("§c💨 §lPlayer is getting BLOWN AWAY by the wind!"), true);
    }

    private void violentGust(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 120, 5, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 2, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 3, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20, 0, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 30, 2, false, true));
        player.hurt(player.damageSources().fall(), 1.5f);
        player.hurt(player.damageSources().fall(), 1.5f);
        player.push(
                (Math.random() - 0.5) * 3.0,
                0.8,
                (Math.random() - 0.5) * 3.0);
        player.hurtMarked = false;
        sendVelocity(player);
        player.displayClientMessage(
                Component.literal("§4§l🌪 THE WIND LAUNCHES THE PLAYER INTO THE AIR!!"), true);
    }
}
