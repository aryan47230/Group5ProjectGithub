package com.example.examplemod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.example.examplemod.item.custom.ChiselItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("cs124uiuc");

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            location -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, location))));

    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            location -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, location))));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            location -> new ChiselItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, location))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}