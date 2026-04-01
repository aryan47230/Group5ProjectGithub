package com.example.examplemod.entity.ClaudeEnemy;

import com.example.examplemod.cs124uiuc;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.CameraRenderState;

public class ClaudeEnemyRenderer extends LivingEntityRenderer<ClaudeEnemy, ClaudeEnemyRenderState, ClaudeEnemyModel> {
    public ClaudeEnemyRenderer(EntityRendererProvider.Context context) {
        super(context, new ClaudeEnemyModel(context.bakeLayer(cs124uiuc.CLAUDE_ENEMY_LAYER)), 0.5f);
        this.addLayer(new ClaudeEnemyRenderLayer(this, context.getModelSet()));
    }

    @Override
    public ClaudeEnemyRenderState createRenderState() {
        return new ClaudeEnemyRenderState();
    }

    @Override
    public void extractRenderState(ClaudeEnemy entity, ClaudeEnemyRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
    }

    @Override
    public void submit(ClaudeEnemyRenderState renderState, PoseStack poseStack, SubmitNodeCollector collector,
            CameraRenderState cameraState) {
        super.submit(renderState, poseStack, collector, cameraState);
    }

    @Override
    public ResourceLocation getTextureLocation(ClaudeEnemyRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID, "textures/entity/claude_enemy/claude_enemy.png");
    }
}
