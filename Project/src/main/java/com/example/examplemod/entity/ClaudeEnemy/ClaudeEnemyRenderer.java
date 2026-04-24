package com.example.examplemod.entity.ClaudeEnemy;

import com.example.examplemod.cs124uiuc;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.CameraRenderState;

public class ClaudeEnemyRenderer extends LivingEntityRenderer<ClaudeEnemy, ClaudeEnemyRenderState, ClaudeEnemyModel> {
    // private ClaudeEnemyRenderState renderState = new ClaudeEnemyRenderState();

    public ClaudeEnemyRenderer(EntityRendererProvider.Context context) {
        super(context, new ClaudeEnemyModel(context.bakeLayer(cs124uiuc.CLAUDE_ENEMY_LAYER)), 0.5f);
        this.addLayer(new ClaudeEnemyRenderLayer(this, context.getModelSet()));
    }

    @Override
    public ClaudeEnemyRenderState createRenderState() {
        return new ClaudeEnemyRenderState(); // this render seems to be active for all ClaudeEntities
    }

    @Override
    public void extractRenderState(ClaudeEnemy entity, ClaudeEnemyRenderState state, float partialTick) {
        // System.out.println("isAttacking: " +
        // entity.getEntityData().get(entity.isAttackingDataAccessor));
        // System.out.println("isIdle: " +
        // entity.getEntityData().get(entity.isIdleDataAccessor));
        if (entity.getEntityData().get(entity.isAttackingDataAccessor)) {
            state.attackAnimationState
                    .startIfStopped(entity.tickCount - entity.getEntityData().get(entity.attackTimer));
        } else if (entity.getEntityData().get(entity.isIdleDataAccessor)) {
            state.idleAnimationState.startIfStopped(entity.tickCount - entity.getEntityData().get(entity.idleTimer));
        } else {
            state.idleAnimationState.stop();
            state.attackAnimationState.stop();
        }
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
