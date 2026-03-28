package com.example.examplemod.entity.client;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.client.animation.Keyframe;

import com.example.examplemod.cs124uiuc;
import com.example.examplemod.entity.custom.GooseEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class GooseModel extends EntityModel<GooseEntityRenderState>{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(cs124uiuc.MODID, "goose"), "main");
	private final ModelPart goos;
	private final ModelPart rleg;
	private final ModelPart lleg;
	private final ModelPart body;
	private final ModelPart rwing;
	private final ModelPart lwing;
	private final ModelPart neck;
	private final ModelPart head;
	private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

	public GooseModel(ModelPart root) {
        super(root);

		this.goos = root.getChild("goose");
		this.rleg = this.goos.getChild("rleg");
		this.lleg = this.goos.getChild("lleg");
		this.body = this.goos.getChild("body");
		this.rwing = this.goos.getChild("rwing");
		this.lwing = this.goos.getChild("lwing");
		this.neck = this.goos.getChild("neck");
		this.head = this.neck.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition goos = partdefinition.addOrReplaceChild("goose", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition rleg = goos.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(18, 17).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(0.5F, 3.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.0F, -1.0F));

		PartDefinition lleg = goos.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 2).addBox(0.5F, 3.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.0F, 1.0F));

		PartDefinition body = goos.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -11.0F, -2.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 17).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 8.0F, 0.0F));

		PartDefinition rwing = goos.addOrReplaceChild("rwing", CubeListBuilder.create(), PartPose.offset(1.0F, -3.0F, -2.0F));

		PartDefinition cube_r1 = rwing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 13).addBox(-2.0F, 4.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition lwing = goos.addOrReplaceChild("lwing", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, -3.0F, 2.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r2 = lwing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 15).addBox(-2.0F, 4.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 8).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.3007F, 0.9537F, -0.3054F, 0.0F, 0.0F));

		PartDefinition neck = goos.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(2.0F, -2.0F, 0.0F));

		PartDefinition cube_r3 = neck.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 17).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.5F, 0.0053F, 0.0079F, 0.7191F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
	
	public void setupAnim(GooseEntityRenderState renderState) {
        super.setupAnim(renderState);
		GooseEntity goose = renderState.entity;

		if (goose != null) {
		this.animate(goose.sitting, GooseAnimations.sitting, renderState.ageInTicks, 1.0F);
		this.animate(goose.walking, GooseAnimations.walking, renderState.ageInTicks, 1.0F);
		this.animate(goose.attacking, GooseAnimations.attacking, renderState.ageInTicks, 1.0F);
		}
	}

	protected void animate(GooseAnimationState p_361867_, AnimationDefinition p_365477_, float p_361961_) {
    	this.animate(p_361867_, p_365477_, p_361961_, 1.0F);
	}

	protected void animateWalk(AnimationDefinition p_363127_, float p_364817_, float p_364163_, float p_365350_, float p_365167_) {
      long i = (long)(p_364817_ * 50.0F * p_365350_);
      float f = Math.min(p_364163_ * p_365167_, 1.0F);
    	animate(this, p_363127_, i, f, ANIMATION_VECTOR_CACHE);
	}

	protected void animate(GooseAnimationState p_368871_, AnimationDefinition p_365491_, float p_363110_, float p_368202_) {
      p_368871_.ifStarted((p_368242_) -> animate(this, p_365491_, (long)((float)p_368242_.getTimeInMillis(p_363110_) * p_368202_), 1.0F, ANIMATION_VECTOR_CACHE));
	}

	protected void applyStatic(AnimationDefinition p_369884_) {
		animate(this, p_369884_, 0L, 1.0F, ANIMATION_VECTOR_CACHE);
	}

	public static Optional<ModelPart> getAnyDescendantWithName(Model p_362391_, String p_363872_) {
    	Stream<ModelPart> stream = p_362391_.root().getAllParts().stream();
    	return p_363872_.equals("root") ? Optional.of(p_362391_.root()) : stream.filter((p_364767_) -> p_364767_.hasChild(p_363872_)).findFirst().map((p_366385_) -> p_366385_.getChild(p_363872_));
	}

	public static void animate(Model p_362391_, AnimationDefinition p_232321_, long p_232322_, float p_232323_, Vector3f p_253861_) {
    	float f = getElapsedSeconds(p_232321_, p_232322_);

    	for(Map.Entry<String, List<AnimationChannel>> entry : p_232321_.boneAnimations().entrySet()) {
        	Optional<ModelPart> optional = getAnyDescendantWithName(p_362391_, (String)entry.getKey());
        	List<AnimationChannel> list = (List)entry.getValue();
        	optional.ifPresent((p_232330_) -> list.forEach((p_288241_) -> {
            	Keyframe[] akeyframe = p_288241_.keyframes();
            	int i = Math.max(0, Mth.binarySearch(0, akeyframe.length, (p_232315_) -> f <= akeyframe[p_232315_].timestamp()) - 1);
            	int j = Math.min(akeyframe.length - 1, i + 1);
            	Keyframe keyframe = akeyframe[i];
            	Keyframe keyframe1 = akeyframe[j];
            	float f1 = f - keyframe.timestamp();
            	float f2;
            	if (j != i) {
                	f2 = Mth.clamp(f1 / (keyframe1.timestamp() - keyframe.timestamp()), 0.0F, 1.0F);
            	} else {
                	f2 = 0.0F;
            	}

				keyframe1.interpolation().apply(p_253861_, f2, akeyframe, i, j, p_232323_);
				p_288241_.target().apply(p_232330_, p_253861_);
				}));
		}

	}

	public static float getElapsedSeconds(AnimationDefinition p_232317_, long p_232318_) {
		float f = (float)p_232318_ / 1000.0F;
		return p_232317_.looping() ? f % p_232317_.lengthInSeconds() : f;
	}

	public ModelPart getHead() {
		return this.head;
	}

	/*/@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		goose.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

    @Override
    public ModelPart root() {
        return goos;
    }
        */
}