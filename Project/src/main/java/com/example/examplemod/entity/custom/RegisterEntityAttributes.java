package com.example.examplemod.entity.custom;

import com.example.examplemod.entity.ModEntities;

import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class RegisterEntityAttributes {
    public RegisterEntityAttributes() {
    }

    @EventBusSubscriber(
        modid = "cs124uiuc"
    )
    public static class ForgeEvents {
        public ForgeEvents() {
        }

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put((EntityType)ModEntities.GOOSE.get(), 
            GooseEntity.createAttributes().build());
        }
    }
}
