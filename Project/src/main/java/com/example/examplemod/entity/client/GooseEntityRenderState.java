package com.example.examplemod.entity.client;

import com.example.examplemod.cs124uiuc;
import com.example.examplemod.entity.custom.GooseEntity;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class GooseEntityRenderState extends LivingEntityRenderState {
   public ResourceLocation skinTexture = ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID, "textures/entity/goose.png");
   public GooseEntity entity;

   public GooseEntityRenderState() {
   }
}