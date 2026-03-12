package com.example.examplemod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.example.examplemod.entity.ModEntities;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("cs124uiuc");
    public static final DeferredHolder<Item, SpawnEggItem> GOOSE_SPAWN_EGG;

    public ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        GOOSE_SPAWN_EGG = ITEMS.register("goose_spawn_egg", () -> new SpawnEggItem((new Item.Properties()).spawnEgg((EntityType)ModEntities.GOOSE.get()).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("cs124uiuc", "goose_spawn_egg")))));
    }
}
