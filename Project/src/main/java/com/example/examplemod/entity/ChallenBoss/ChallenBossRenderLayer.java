package com.example.examplemod.entity.ChallenBoss;

import static com.example.examplemod.cs124uiuc.CHALLEN_BOSS;
import static com.example.examplemod.cs124uiuc.CHALLEN_BOSS_LAYER;

import com.example.examplemod.cs124uiuc;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import com.example.examplemod.entity.ChallenBoss.ChallenBossRenderer;

public class ChallenBossRenderLayer extends RenderLayer<ChallenBossRenderState, ChallenBossModel> {
    private final ChallenBossModel model;

    public ChallenBossRenderLayer(ChallenBossRenderer renderer, EntityModelSet entityModelSet) {
        super(renderer);
        this.model = new ChallenBossModel(entityModelSet.bakeLayer(CHALLEN_BOSS_LAYER));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords,
            ChallenBossRenderState renderState, float yRot, float xRot) {
        collector
                .order(1)
                .submitModel(this.model, renderState, poseStack,
                        RenderType.entitySolid(ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID,
                                "textures/entity/challen_boss/challen_boss.png")),
                        lightCoords, (int) yRot, (int) xRot, null);
    }

}
