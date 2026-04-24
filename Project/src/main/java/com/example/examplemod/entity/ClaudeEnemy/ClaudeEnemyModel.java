package com.example.examplemod.entity.ClaudeEnemy;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.KeyframeAnimations;
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
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

// Made with Blockbench 5.1.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ClaudeEnemyModel extends EntityModel<ClaudeEnemyRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath("cs124uiuc", "claude_enemy"), "main");
	private final ModelPart torso;
	private final ModelPart middle;
	private final ModelPart upper;
	private final ModelPart lower;
	private final ModelPart legs;
	private final ModelPart footr;
	private final ModelPart footl;
	private final ModelPart heads;
	private final ModelPart bar;

	public static final AnimationDefinition idle = AnimationDefinition.Builder.withLength(2.0F).looping()
			.addAnimation("heads", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.979F, 0.0806F, -2.8358F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.2917F, KeyframeAnimations.degreeVec(1.75F, 1.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(2.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("heads", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5417F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("lower", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -180.0F, 180.0F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -360.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("lower", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.posVec(-5.94F, 2.56F, -0.03F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.posVec(-12.0F, 15.0F, -1.0F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.posVec(-4.4F, 25.65F, -2.88F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.26F, 26.0F, -2.96F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2917F, KeyframeAnimations.posVec(-8.57F, 22.7F, -2.23F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.5F, KeyframeAnimations.posVec(-13.29F, 11.0F, -1.48F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.75F, KeyframeAnimations.posVec(-6.57F, 1.73F, 0.31F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("middle", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(7.8062F, 7.3998F, 46.4877F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("middle", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 47.5F, 0.0F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F),
							AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bar", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bar", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition attack = AnimationDefinition.Builder.withLength(1.0F)
			.addAnimation("footr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.degreeVec(11.765F, 3.4087F, 12.1365F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.degreeVec(11.765F, 3.4087F, 12.1365F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("footr", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.posVec(-3.0F, -1.0F, 1.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.posVec(-3.0F, -1.0F, 1.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("footl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.degreeVec(4.9574F, 0.6518F, -7.4718F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.degreeVec(4.9574F, 0.6518F, -7.4718F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("footl", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("heads", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.degreeVec(-7.352F, -1.484F, 0.008F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 360.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 720.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("heads", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.posVec(0.0F, 4.0F, -4.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 4.0F, -4.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.posVec(0.0F, 4.0F, -4.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("middle", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.2506F, -1.0448F, -7.425F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.degreeVec(0.2506F, -1.0448F, -7.425F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("middle", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.degreeVec(12.8643F, 1.8723F, 11.9977F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.degreeVec(12.8643F, 1.8723F, 11.9977F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.posVec(1.0F, 1.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.posVec(1.0F, 1.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bar", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.degreeVec(-2.4058F, -0.6784F, 0.0622F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.degreeVec(-2.4058F, -0.6784F, 0.0622F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bar", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bar", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.scaleVec(1.6F, 1.2F, 1.3F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9167F, KeyframeAnimations.scaleVec(1.6F, 1.2F, 1.3F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition Walk = AnimationDefinition.Builder.withLength(3.0F).looping()
			.addAnimation("footr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.25F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("footr", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("footl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.25F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("footl", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.25F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("heads", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 35.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("lower", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("lower", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 2.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("middle", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("middle", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(-2.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 65.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bar", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bar", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
							AnimationChannel.Interpolations.LINEAR)))
			.build();

	private final KeyframeAnimation idleKeyFrame;
	private final KeyframeAnimation walkKeyFrame;
	private final KeyframeAnimation attackKeyFrame;

	public ClaudeEnemyModel(ModelPart root) {
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

		this.idleKeyFrame = idle.bake(root);
		this.walkKeyFrame = Walk.bake(root);
		this.attackKeyFrame = attack.bake(root);
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
						CubeListBuilder.create().texOffs(12, 20).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 11.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition footl = legs.addOrReplaceChild("footl", CubeListBuilder.create().texOffs(20, 4).addBox(5.0F,
				-1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -1.0F, 0.0F));

		PartDefinition leg2_r1 = footl
				.addOrReplaceChild("leg2_r1",
						CubeListBuilder.create().texOffs(16, 20).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 11.0F, 1.0F,
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

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(ClaudeEnemyRenderState state) {
		super.setupAnim(state);
		if (state.attackAnimationState.isStarted()) {
			attackKeyFrame.apply(state.attackAnimationState, state.ageInTicks);
		} else if (state.idleAnimationState.isStarted()) {
			idleKeyFrame.apply(state.idleAnimationState, state.ageInTicks);
		} else {
			walkKeyFrame.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 1, 1);
		}
	}

	// @Override
	// public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount,
	// float ageInTicks, float netHeadYaw, float headPitch) {

	// }

	// @Override
	// public void renderToBuffer(PoseStack poseStack, VertexConsumer
	// vertexConsumer, int packedLight, int packedOverlay, float red, float green,
	// float blue, float alpha) {
	// torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red,
	// green, blue, alpha);
	// legs.render(poseStack, vertexConsumer, packedLight, packedOverlay, red,
	// green, blue, alpha);
	// heads.render(poseStack, vertexConsumer, packedLight, packedOverlay, red,
	// green, blue, alpha);
	// bar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green,
	// blue, alpha);
	// }
}