package com.example.examplemod.event;

import com.example.examplemod.cs124uiuc;
import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.entity.custom.GooseEntity;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation;

public class ModEvents {
    public ModEvents() {
    }

    @EventBusSubscriber(
        modid = cs124uiuc.MODID
    )
    public static class ForgeEvents {
        public ForgeEvents() {
    }

    @SubscribeEvent
    public static void spawnPlacementEvent(RegisterSpawnPlacementsEvent event) {
        event.register((EntityType)ModEntities.GOOSE.get(), SpawnPlacementTypes.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, GooseEntity::checkGooseSpawnRules, Operation.OR);
    }
}
}
