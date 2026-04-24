package com.example.examplemod.block;

import com.example.examplemod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.example.examplemod.block.custom.MagicBlock;
import com.example.examplemod.block.custom.PedestalBlock;
import com.example.examplemod.block.custom.ComputerBlock;

import java.util.function.Function;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks("cs124uiuc");

    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
            location -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, location))
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock("bismuth_ore",
            location -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, location))
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

     public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE = registerBlock("bismuth_deepslate_ore",
            location -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, location))
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

        public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
                location -> new MagicBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, location))
                    .strength(2f).requiresCorrectToolForDrops()));

        public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal",
                location -> new PedestalBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, location))
                    .strength(2f).requiresCorrectToolForDrops()));

        public static final DeferredBlock<Block> COMPUTER = registerBlock("computer",
                location -> new ComputerBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, location))
                    .strength(2f).requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<ResourceLocation, T> blockFactory) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, blockFactory);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, location ->
                new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, location))));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}