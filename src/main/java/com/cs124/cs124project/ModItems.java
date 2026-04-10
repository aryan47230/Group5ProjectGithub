package com.cs124.cs124project;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredSpawnEggItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
        DeferredRegister.createItems("cs124project");

    public static final DeferredItem<CoolItem> MY_ITEM =
        ITEMS.registerItem("cool", properties -> new CoolItem(properties));

    public static final DeferredItem<DeferredSpawnEggItem> ZOMBIE_SPAWN_EGG =
        ITEMS.registerItem("zombie_spawn_egg", properties ->
        new DeferredSpawnEggItem(EntityType.ZOMBIE, 0x00afaf, 0x799c65, properties));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

