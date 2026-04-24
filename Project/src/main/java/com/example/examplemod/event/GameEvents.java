package com.example.examplemod.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

public class GameEvents {
    public GameEvents() {
    }

    @EventBusSubscriber(
        modid = "cs124uiuc"
    )
    public static class ForgeGameEvents {
        public ForgeGameEvents() {
        }

        @SubscribeEvent
        public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        }
    }
}
