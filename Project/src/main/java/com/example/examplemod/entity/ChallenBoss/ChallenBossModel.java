package com.example.examplemod.entity.ChallenBoss;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import com.example.examplemod.cs124uiuc;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ChallenBossModel extends EntityModel<ChallenBossRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID, "challen_boss"), "main");
	private final ModelPart torso;
	private final ModelPart middle;
	private final ModelPart upper;
	private final ModelPart lower;
	private final ModelPart legs;
	private final ModelPart footr;
	private final ModelPart footl;
	private final ModelPart heads;
	private final ModelPart bar;

	public ChallenBossModel(ModelPart root) {
		super(root);
		this.torso = root.getChild("torso");
		this.middle = this.torso.getChild("middle");
		this.upper = this.torso.getChild("upper");
		this.lower = this.torso.getChild("lower");
		this.legs = root.getChild("legs");
		this.footr = this.legs.getChild("footr");
		this.footl = this.legs.getChild("footl");
		this.heads = root.getChild("heads");
		this.bar = root.getChild("bar");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition middle = torso.addOrReplaceChild("middle", CubeListBuilder.create(),
				PartPose.offset(0.0F, -16.0F, -1.0F));

		PartDefinition middle_r1 = middle.addOrReplaceChild("middle_r1",
				CubeListBuilder.create().texOffs(12, 7).addBox(-1.0F, -3.0F, -1.0F, 4.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0008F, 0.0315F, 0.0814F));

		PartDefinition upper = torso.addOrReplaceChild("upper", CubeListBuilder.create(),
				PartPose.offset(0.0F, -19.0F, -2.0F));

		PartDefinition upper_r1 = upper.addOrReplaceChild("upper_r1",
				CubeListBuilder.create().texOffs(0, 21).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1746F, -0.0435F, -0.258F));

		PartDefinition lower = torso.addOrReplaceChild("lower", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition lower_r1 = lower.addOrReplaceChild("lower_r1",
				CubeListBuilder.create().texOffs(20, 21).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -11.0F, -2.0F, 0.0419F, 0.0082F, -0.078F));

		PartDefinition legs = partdefinition.addOrReplaceChild("legs", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition footr = legs.addOrReplaceChild("footr", CubeListBuilder.create().texOffs(14, 4).addBox(-7.0F,
				-1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -1.0F, 0.0F));

		PartDefinition leg1_r1 = footr
				.addOrReplaceChild("leg1_r1",
						CubeListBuilder.create().texOffs(12, 21).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 11.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition footl = legs.addOrReplaceChild("footl", CubeListBuilder.create().texOffs(20, 4).addBox(5.0F,
				-1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -1.0F, 0.0F));

		PartDefinition leg2_r1 = footl
				.addOrReplaceChild("leg2_r1",
						CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 11.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition heads = partdefinition.addOrReplaceChild("heads", CubeListBuilder.create(),
				PartPose.offset(0.0F, -4.0F, -2.0F));

		PartDefinition headback_r1 = heads.addOrReplaceChild("headback_r1",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, 0.0F, 6.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0738F, 3.1536F, -0.3914F, 0.2182F, 0.0F, 0.0F));

		PartDefinition headfront_r1 = heads.addOrReplaceChild("headfront_r1",
				CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -6.0F, -1.0F, 4.0F, 6.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0738F, 3.1536F, 0.6086F, 0.1309F, 0.0F, 0.0F));

		PartDefinition bar = partdefinition.addOrReplaceChild("bar", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bar4_r1 = bar.addOrReplaceChild("bar4_r1",
				CubeListBuilder.create().texOffs(14, 17).addBox(-5.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(9.0F, -24.0F, -1.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bar3_r1 = bar.addOrReplaceChild("bar3_r1",
				CubeListBuilder.create().texOffs(14, 0).addBox(-5.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, -23.0F, -2.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bar2_r1 = bar.addOrReplaceChild("bar2_r1",
				CubeListBuilder.create().texOffs(12, 13).addBox(-5.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -23.0F, -1.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition bar1_r1 = bar.addOrReplaceChild("bar1_r1",
				CubeListBuilder.create().texOffs(0, 17).addBox(-5.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, -24.0F, -1.0F, -0.2182F, 0.0F, 0.1745F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(ChallenBossRenderState renderState) {

	}
	// public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount,
	// float ageInTicks, float netHeadYaw,
	// float headPitch) {

	// }

	// @Override
	// public void renderToBuffer(PoseStack poseStack, VertexConsumer
	// vertexConsumer, int packedLight, int packedOverlay) {
	// torso.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	// legs.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	// heads.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	// bar.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	// }
}