package com.example.examplemod.item;

import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.item.custom.ChiselItem;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("cs124uiuc");

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            location -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, location))));

    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            location -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, location))));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            location -> new ChiselItem(new Item.Properties().durability(32).setId(ResourceKey.create(Registries.ITEM, location))));

    public static final DeferredHolder<Item, SpawnEggItem> GOOSE_SPAWN_EGG;
    public static final DeferredItem<Item> GOLDEN_EGG;

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        GOOSE_SPAWN_EGG = ITEMS.register("goose_spawn_egg", () -> new SpawnEggItem(
                new Item.Properties()
                        .spawnEgg((EntityType) ModEntities.GOOSE.get())
                        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("cs124uiuc", "goose_spawn_egg")))));

        GOLDEN_EGG = ITEMS.registerSimpleItem(
                "golden_egg",
                props -> props.component(
                        DataComponents.CONSUMABLE,
                        Consumable.builder()
                                .consumeSeconds(4f)
                                .animation(ItemUseAnimation.EAT)
                                .hasConsumeParticles(true)
                                .onConsume(new ApplyStatusEffectsConsumeEffect(
                                        new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 60000, 0), 0.3F))
                                .build()));
    }
}
