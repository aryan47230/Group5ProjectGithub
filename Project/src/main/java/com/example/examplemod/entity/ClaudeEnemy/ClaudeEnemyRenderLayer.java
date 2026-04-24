package com.example.examplemod.entity.ClaudeEnemy;

import static com.example.examplemod.cs124uiuc.CLAUDE_ENEMY;
import static com.example.examplemod.cs124uiuc.CLAUDE_ENEMY_LAYER;

import com.example.examplemod.cs124uiuc;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemyRenderer;
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

public class ClaudeEnemyRenderLayer extends RenderLayer<ClaudeEnemyRenderState, ClaudeEnemyModel> {
    private final ClaudeEnemyModel model;

    public ClaudeEnemyRenderLayer(ClaudeEnemyRenderer renderer, EntityModelSet entityModelSet) {
        super(renderer);
        this.model = new ClaudeEnemyModel(entityModelSet.bakeLayer(CLAUDE_ENEMY_LAYER));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords,
            ClaudeEnemyRenderState renderState, float yRot, float xRot) {
        collector
                .order(0)
                .submitModel(this.model, renderState, poseStack,
                        RenderType.entitySolid(ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID,
                                "textures/entity/claude_enemy/claude_enemy.png")),
                        lightCoords, 0, renderState.outlineColor, null);
    }

}
