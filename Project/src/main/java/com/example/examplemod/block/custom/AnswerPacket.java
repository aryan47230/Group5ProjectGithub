package com.example.examplemod.block.custom;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record AnswerPacket(int choiceIndex) implements CustomPacketPayload {
    public static final Type<AnswerPacket> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath("cs124uiuc", "answer_packet"));
    public static final StreamCodec<ByteBuf, AnswerPacket> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.INT, AnswerPacket::choiceIndex,
        AnswerPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
