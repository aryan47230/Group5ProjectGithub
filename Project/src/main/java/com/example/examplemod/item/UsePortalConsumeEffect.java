package com.example.examplemod.item;

import java.util.Set;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.portal.PortalForcer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.portal.TeleportTransition.PostTeleportTransition;
import net.minecraft.commands.Commands;
import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.world.item.consume_effects.ConsumeEffect.Type;
import net.minecraft.world.item.consume_effects.TeleportRandomlyConsumeEffect;

import com.example.examplemod.cs124uiuc;

public record UsePortalConsumeEffect(float diameter)
    implements ConsumeEffect, Portal {

    public static final MapCodec<UsePortalConsumeEffect> CODEC = RecordCodecBuilder.mapCodec((p_366612_) -> p_366612_.group(ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("diameter", 16.0F).forGetter(UsePortalConsumeEffect::diameter)).apply(p_366612_, UsePortalConsumeEffect::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, UsePortalConsumeEffect> STREAM_CODEC;

    public UsePortalConsumeEffect() {
        this(8.0f);
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
        //if (entity.canUsePortal(false)) {
        entity.setAsInsidePortal(this, entity.blockPosition());

        // Can successfully use portal
        return true;
        //}

        // Cannot use portal
        //return false;
    }

    @Override
    public ConsumeEffect.Type<? extends ConsumeEffect> getType() {
        // Set to registered object
        return cs124uiuc.USE_PORTAL.get();
    }

    @Override
    @Nullable
    public TeleportTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        // Set teleport location
        return TeleportTransition.createDefault((ServerPlayer) entity, TeleportTransition.PLAY_PORTAL_SOUND.then((p_351967_) -> p_351967_.placePortalTicket(pos)));
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.FLOAT, UsePortalConsumeEffect::diameter, UsePortalConsumeEffect::new);
    }
}