package com.example.examplemod;

import com.example.examplemod.entity.client.GooseModel;
import com.example.examplemod.entity.client.GooseRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import com.example.examplemod.entity.ModEntities;

@EventBusSubscriber(
    modid = "cs124uiuc",
    value = {Dist.CLIENT}
)
    public class ClientInit {
        public ClientInit() {
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer((EntityType)ModEntities.GOOSE.get(), (m) -> new GooseRenderer(m));
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GooseModel.LAYER_LOCATION, GooseModel::createBodyLayer);
    }
}
