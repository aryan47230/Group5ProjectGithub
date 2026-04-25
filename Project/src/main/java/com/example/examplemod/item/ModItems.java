package com.example.examplemod.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.item.consume_effects.TeleportRandomlyConsumeEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.PotionItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.component.DataComponents;

import javax.annotation.Nonnull;

import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.sound.ModSounds;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.core.Holder;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("cs124uiuc");
    public static final DeferredHolder<Item, SpawnEggItem> GOOSE_SPAWN_EGG;
    public static final DeferredItem<Item> GOLDEN_EGG;
    public static final DeferredItem<Item> EVIL_EGG;
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.registerSimpleItem("raw_bismuth");
    public static final DeferredItem<Item> BISMUTH = ITEMS.registerSimpleItem("bismuth");
    public static final DeferredItem<Item> CHISEL = ITEMS.registerSimpleItem("chisel");
    public static final DeferredItem<Item> ID = ITEMS.registerSimpleItem("id");

    public ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        GOOSE_SPAWN_EGG = ITEMS.register("goose_spawn_egg",
                () -> new SpawnEggItem((new Item.Properties()).spawnEgg((EntityType) ModEntities.GOOSE.get())
                        .setId(ResourceKey.create(Registries.ITEM,
                                ResourceLocation.fromNamespaceAndPath("cs124uiuc", "goose_spawn_egg")))));
        GOLDEN_EGG = ITEMS.registerSimpleItem(
                "golden_egg",
                props -> props.component(
                        DataComponents.CONSUMABLE,
                        Consumable.builder()
                                .consumeSeconds(2f)
                                .animation(ItemUseAnimation.EAT)
                                .hasConsumeParticles(false)
                                .onConsume(
                                        new UsePortalConsumeEffect())
                                .build()));
        EVIL_EGG = ITEMS.registerSimpleItem("evil_egg", (props) -> props.component(DataComponents.CONSUMABLE, Consumable.builder().consumeSeconds(2.0F).animation(ItemUseAnimation.EAT).sound(SoundEvents.ARMOR_EQUIP_CHAIN).soundAfterConsume(SoundEvents.ARMOR_EQUIP_CHAIN).hasConsumeParticles(true).onConsume(
                // When finished consuming, applies the effects with a 30% chance
                new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 600, 0), 1.0F)
            ).onConsume(new UseUIUCConsumeEffect(-0.5F)).build()));
    }
}
