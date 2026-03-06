package com.example.examplemod.entity;

import com.example.examplemod.cs124uiuc;
import com.example.examplemod.entity.custom.GooseEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType.Builder;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES;
    public static final Supplier<EntityType<GooseEntity>> GOOSE;

    /*
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, cs124uiuc.MODID);

    public static final Supplier<EntityType<GooseEntity>> GOOSE =
            ENTITY_TYPES.register(
                "goose", 
                () -> EntityType.Builder.of(
                    GooseEntity::new, 
                    MobCategory.MISC
                )
                .sized(0.5f, 0.5f)
                .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNameSpaceAndPath("cs124uiuc", "goose"))));
    */

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    static {
        ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, cs124uiuc.MODID);
        GOOSE = ENTITY_TYPES.register("goose", () -> Builder.of(GooseEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(50).setUpdateInterval(3).sized(0.5F, 0.5F).build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("cs124uiuc", "goose"))));
    }
}