package com.example.examplemod.entity.client;

import org.jetbrains.annotations.NotNull;

import com.example.examplemod.cs124uiuc;
import com.example.examplemod.entity.custom.GooseEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class GooseModel extends EntityModel<GooseEntityRenderState>{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID, "goose"), "main");
	private final ModelPart goose;
	private final ModelPart rleg;
	private final ModelPart lleg;
	private final ModelPart body;
	private final ModelPart rwing;
	private final ModelPart lwing;
	private final ModelPart neck;
	private final ModelPart head;

	public GooseModel(ModelPart root) {
        super(root);

		this.goose = root.getChild("goose");
		this.rleg = this.goose.getChild("rleg");
		this.lleg = this.goose.getChild("lleg");
		this.body = this.goose.getChild("body");
		this.rwing = this.goose.getChild("rwing");
		this.lwing = this.goose.getChild("lwing");
		this.neck = this.goose.getChild("neck");
		this.head = this.neck.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition goose = partdefinition.addOrReplaceChild("goose", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition rleg = goose.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(18, 17).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(0.5F, 3.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -4.0F, -1.0F));

		PartDefinition lleg = goose.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 2).addBox(0.5F, 3.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -4.0F, 1.0F));

		PartDefinition body = goose.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -11.0F, -2.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 17).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 3.0F, 0.0F));

		PartDefinition rwing = goose.addOrReplaceChild("rwing", CubeListBuilder.create(), PartPose.offset(1.0F, -8.0F, -2.0F));

		PartDefinition cube_r1 = rwing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 13).addBox(-2.0F, 4.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition lwing = goose.addOrReplaceChild("lwing", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -8.0F, 2.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r2 = lwing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 15).addBox(-2.0F, 4.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 8).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.3007F, 0.9537F, -0.3054F, 0.0F, 0.0F));

		PartDefinition neck = goose.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(2.0F, -7.0F, 0.0F));

		PartDefinition cube_r3 = neck.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 17).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.5F, 0.0053F, 0.0079F, 0.7191F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public void setupAnim(@NotNull GooseEntityRenderState renderState) {
        //this.root().getAllParts().forEach(ModelPart::resetPose);
        //this.animateWalk(GooseAnimations.walk, limbSwing, limbSwingAmount, 1f, 1f);
	}

	/*/@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		goose.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

    @Override
    public ModelPart root() {
        return goose;
    }
        */
}