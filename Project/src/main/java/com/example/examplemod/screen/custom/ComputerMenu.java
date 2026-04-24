package com.example.examplemod.screen.custom;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.block.entity.ComputerBlockEntity;
import com.example.examplemod.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

public class ComputerMenu extends AbstractContainerMenu {

    private final BlockPos blockPos;

    // Client-side constructor (from network)
    public ComputerMenu(int windowId, Inventory inv, FriendlyByteBuf buf) {
        this(windowId, inv, buf.readBlockPos());
    }

    // Server-side constructor
    public ComputerMenu(int windowId, Inventory inv, BlockPos pos) {
        super(ModRegistry.COMPUTER_MENU.get(), windowId);
        this.blockPos = pos;
    }

    public BlockPos getBlockPos() { return blockPos; }

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(
            blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5
        ) < 64.0;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}