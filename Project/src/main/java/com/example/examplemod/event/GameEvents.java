package com.example.examplemod.event;

import com.example.examplemod.block.custom.IDScannerBlock;
import com.example.examplemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class GameEvents {
    public GameEvents() {
    }

    @EventBusSubscriber(
        modid = "cs124uiuc"
    )
    public static class ForgeGameEvents {
        public ForgeGameEvents() {
        }

        @SubscribeEvent
        public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        }

        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);

            if (state.getBlock() instanceof IDScannerBlock
                    && event.getItemStack().getItem() == ModItems.ID.get()
                    && !level.isClientSide()
                    && !state.getValue(IDScannerBlock.POWERED)) {
                level.setBlock(pos, state.setValue(IDScannerBlock.POWERED, true), 3);
                ((ServerLevel) level).scheduleTick(pos, state.getBlock(), 20);
                level.updateNeighborsAt(pos, state.getBlock());
                event.setCanceled(true);
            }
        }
    }
}
