package com.example.examplemod.entity;

//import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.entity.client.GooseRenderer;

@EventBusSubscriber({Dist.CLIENT})
public class ModEntityRenderers {
    public ModEntityRenderers() {
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
      event.registerEntityRenderer((EntityType)ModEntities.GOOSE.get(), GooseRenderer::new);
    }
}
