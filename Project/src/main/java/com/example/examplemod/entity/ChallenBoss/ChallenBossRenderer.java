package com.example.examplemod.entity.ChallenBoss;

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

public class ChallenBossRenderer extends LivingEntityRenderer<ChallenBoss, ChallenBossRenderState, ChallenBossModel> {
    public ChallenBossRenderer(EntityRendererProvider.Context context) {
        super(context, new ChallenBossModel(context.bakeLayer(cs124uiuc.CHALLEN_BOSS_LAYER)), 0.5f);
        this.addLayer(new ChallenBossRenderLayer(this, context.getModelSet()));
    }

    @Override
    public ChallenBossRenderState createRenderState() {
        return new ChallenBossRenderState();
    }

    @Override
    public void extractRenderState(ChallenBoss entity, ChallenBossRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
    }

    @Override
    public void submit(ChallenBossRenderState renderState, PoseStack poseStack, SubmitNodeCollector collector,
            CameraRenderState cameraState) {
        super.submit(renderState, poseStack, collector, cameraState);
    }

    @Override
    public ResourceLocation getTextureLocation(ChallenBossRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID, "textures/entity/challen_boss/challen_boss.png");
    }
}
