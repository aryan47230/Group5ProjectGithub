package com.example.examplemod;

import com.example.examplemod.block.custom.AnswerPacket;
import com.example.examplemod.block.entity.ComputerBlockEntity;
import com.example.examplemod.screen.custom.ComputerMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PacketHandler {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PacketHandler::registerPayloads);
    }

    private static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
            AnswerPacket.TYPE,
            AnswerPacket.STREAM_CODEC,
            (payload, ctx) -> ctx.enqueueWork(() -> {
                ServerPlayer player = (ServerPlayer) ctx.player();
                if (player.containerMenu instanceof ComputerMenu menu) {
                    BlockEntity be = player.level().getBlockEntity(menu.getBlockPos());
                    if (be instanceof ComputerBlockEntity computer) {
                        computer.rewardPlayer(player);
                    }
                }
            })
        );
    }
}
