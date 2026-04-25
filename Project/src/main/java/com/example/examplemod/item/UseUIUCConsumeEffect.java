package com.example.examplemod.item;

// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).

import com.example.examplemod.cs124uiuc;
import com.example.examplemod.dimension.ModDimensions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;

public record UseUIUCConsumeEffect(float diameter) implements ConsumeEffect, Portal {
    public static final MapCodec<UseUIUCConsumeEffect> CODEC = RecordCodecBuilder.mapCodec((p_366612_) -> p_366612_.group(ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("diameter", 16.0F).forGetter(UseUIUCConsumeEffect::diameter)).apply(p_366612_, UseUIUCConsumeEffect::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, UseUIUCConsumeEffect> STREAM_CODEC;

    public UseUIUCConsumeEffect() {
        this(-0.5F);
    }

    public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
        ServerLevel netherDimension = ((ServerPlayer)entity).level().getServer().getLevel(ModDimensions.ILLINI_DIM_KEY);
        ((ServerPlayer)entity).teleportTo(netherDimension, (double)120.0F, (double)120.0F, (double)120.0F, Set.of(), 1.0F, 1.0F, true);
        entity.setAsInsidePortal(this, entity.blockPosition());
        return true;
    }

    public ConsumeEffect.Type<? extends ConsumeEffect> getType() {
        return (ConsumeEffect.Type)cs124uiuc.USE_UIUC.get();
    }

    @Nullable
    public TeleportTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        return new TeleportTransition(level.getServer().getLevel(ModDimensions.ILLINI_DIM_KEY), new Vec3((double)0.0F, (double)120.0F, (double)0.0F), new Vec3((double)0.0F, (double)0.0F, (double)0.0F), 0.0F, 0.0F, TeleportTransition.PLAY_PORTAL_SOUND.then((p_351967_) -> p_351967_.placePortalTicket(pos)));
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.FLOAT, UseUIUCConsumeEffect::diameter, UseUIUCConsumeEffect::new);
    }
}
