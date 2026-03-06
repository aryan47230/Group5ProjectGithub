package com.example.examplemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import org.jetbrains.annotations.NotNull;

import com.example.examplemod.cs124uiuc;
import com.example.examplemod.entity.custom.GooseEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.example.examplemod.entity.client.GooseModel;
import com.example.examplemod.entity.client.GooseEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GooseRenderer extends MobRenderer<GooseEntity, GooseEntityRenderState, GooseModel> {
    public GooseRenderer(EntityRendererProvider.Context context) {
        super(context, new GooseModel(context.bakeLayer(GooseModel.LAYER_LOCATION)), 0.5F);
    }

    public @NotNull GooseEntityRenderState createRenderState() {
        return new GooseEntityRenderState();
    }

    //@Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GooseEntityRenderState gooseEntityRenderState) {
        return gooseEntityRenderState.skinTexture;
    }

    
}
