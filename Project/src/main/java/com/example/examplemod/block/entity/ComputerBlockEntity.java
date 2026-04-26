package com.example.examplemod.block.entity;

import com.example.examplemod.item.ModItems;
import com.example.examplemod.screen.custom.ComputerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ComputerBlockEntity extends BlockEntity implements MenuProvider {

    public ComputerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COMPUTER_BE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Computer Terminal");
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
        return new ComputerMenu(windowId, inv, this.worldPosition);
    }

    public void rewardPlayer(Player player) {
        ItemStack reward = new ItemStack(ModItems.GOOSE_SPAWN_EGG.get());
        if (!player.getInventory().add(reward)) {
            player.drop(reward, false);
        }
    }
}
