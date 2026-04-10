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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemDisplayContext;

public class GooseRenderer extends MobRenderer<GooseEntity, GooseEntityRenderState, GooseModel> {
    public GooseRenderer(EntityRendererProvider.Context context) {
        super(context, new GooseModel(context.bakeLayer(GooseModel.LAYER_LOCATION)), 0.5F);
    }

    public GooseEntityRenderState createRenderState() {
        return new GooseEntityRenderState();
    }

    //@Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GooseEntityRenderState gooseEntityRenderState) {
        return gooseEntityRenderState.skinTexture;
    }

    public void extractRenderState(GooseEntity p_363999_, GooseEntityRenderState p_366002_, float p_362989_) {
        super.extractRenderState(p_363999_, p_366002_, p_362989_);
        //this.itemModelResolver.updateForLiving(p_366002_.rightHandItem, p_363999_.getMainHandItem(), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, p_363999_);
        p_366002_.entity = p_363999_;
    }
}
