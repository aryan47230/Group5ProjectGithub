package com.example.examplemod.dimension;

import com.example.examplemod.cs124uiuc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Registry;

public class ModDimensions {
    public static final ResourceKey<Level> ILLINI_DIM_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID, "illini_dim"));
    public static final ResourceKey<DimensionType> ILLINI_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ILLINI_DIM_KEY.registry());

    public static void register() {
        cs124uiuc.LOGGER.info("Registering ModDimensions for " + cs124uiuc.MODID);
    }

}