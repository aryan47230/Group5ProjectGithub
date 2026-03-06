package com.example.examplemod.entity;

import com.example.examplemod.entity.client.GooseModel;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber({Dist.CLIENT})
public class ModModels {
   public ModModels() {
   }

   @SubscribeEvent
   public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
      event.registerLayerDefinition(GooseModel.LAYER_LOCATION, GooseModel::createBodyLayer);
   }
}
