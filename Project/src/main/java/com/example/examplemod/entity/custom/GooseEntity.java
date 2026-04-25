package com.example.examplemod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Holder;

import org.jetbrains.annotations.NotNull;

import com.example.examplemod.Config;
import com.example.examplemod.entity.client.GooseAnimationState;
import com.example.examplemod.sound.ModSounds;

public class GooseEntity extends Monster {
    public static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

    //public static final AnimationState idleAnimationState = new AnimationState();
    public final GooseAnimationState sitting = new GooseAnimationState();
    public final GooseAnimationState walking = new GooseAnimationState();
    public final GooseAnimationState attacking = new GooseAnimationState();
    //private int idleAnimationTimeout = 0;
    
    public GooseEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        //this.setSize(1.0F, 1.0F);
        this.xpReward = 5;
    }

    public boolean checkSpawnObstruction(LevelReader p_364787_) {
        return p_364787_.isUnobstructed(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.75F, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this, new Class[]{GooseEntity.class})).setAlertOthers(new Class[]{GooseEntity.class}));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, WaterAnimal.class, true));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, Animal.class, true));
    }

    protected void registerCustomGoals() {
    }

    protected void dropCustomDeathLoot(@NotNull ServerLevel level, @NotNull DamageSource damageSource, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, damageSource, recentlyHit);
    }

    public SoundEvent getAmbientSound() {
        return (SoundEvent)ModSounds.HONK.get();
    }

    public void playStepSound(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        this.playSound((SoundEvent)((Holder.Reference)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.cave_vines.step")).orElseThrow()).value(), 0.15F, 1.0F);
    }

    public @NotNull SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return (SoundEvent)ModSounds.GOOSE_HURT.get();
    }

    public @NotNull SoundEvent getDeathSound() {
        return (SoundEvent)ModSounds.GOOSE_DIE.get();
    }

    public boolean onClimbable() {
        return this.isClimbing();
    }

    public void aiStep() {
        super.aiStep();
        if (this.level().isClientSide() && this.animationEnded(this.attacking, 0.9F)) {
            Vec3 vel = this.getDeltaMovement();
            if (Math.abs(vel.x()) + Math.abs(vel.y()) + Math.abs(vel.z()) > (double)0.1F) {
                this.sitting.stop();
                this.walking.stop();
                this.attacking.start(this.tickCount);
            } else {
                this.walking.stop();
                this.attacking.stop();
                this.sitting.start(this.tickCount);
            }
        }

    }

    public void handleEntityEvent(byte p_397414_) {
        if (p_397414_ == 4 && this.animationEnded(this.attacking, 0.9F)) {
            this.attacking.stop();
            this.sitting.stop();
            this.walking.start(this.tickCount);
        }

    }


    public boolean isClimbing() {
        return ((Byte)this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean pClimbing) {
        byte b0 = (Byte)this.entityData.get(DATA_FLAGS_ID);
        if (pClimbing) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    protected @NotNull PathNavigation createNavigation(@NotNull Level pLevel) {
        return new WallClimberNavigation(this, pLevel);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
    }

    public boolean hurtServer(@NotNull ServerLevel serverLevel, DamageSource damageSource, float amount) {
        if (damageSource.is(DamageTypes.FALL)) {
            return false;
        } else if (damageSource.is(DamageTypes.DROWN)) {
            return false;
        } else {
            return damageSource.is(DamageTypes.WITHER) ? false : super.hurtServer(serverLevel, damageSource, amount);
        }
    }

    public boolean doHurtTarget(@NotNull ServerLevel serverLevel, @NotNull Entity target) {
        boolean bl = super.doHurtTarget(serverLevel, target);
        this.level().broadcastEntityEvent(this, (byte)4);
        this.playSound((SoundEvent)ModSounds.HONK_ANGRY.get(), 1.0F, 1.0F);
        return bl;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, (double)20.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.FOLLOW_RANGE, (double)50.F)
                .add(Attributes.ATTACK_DAMAGE, (double)3.0F)
                .add(Attributes.ARMOR, (double)0.0F)
                .add(Attributes.ATTACK_KNOCKBACK, (double)8.0F)
                .add(Attributes.STEP_HEIGHT, (double)1.0F)
                .add(Attributes.SCALE, (double)2.0f);
    }

    private void setupAnimationStates() {
        
    }

    public boolean animationEnded(GooseAnimationState state, float animLength) {
        return (float)state.getTimeInMillis((float)this.tickCount) > animLength * 1000.0F;
    }

    //biomes it spawns in here
    public static boolean checkGooseSpawnRules(EntityType<GooseEntity> gooseEntityType, ServerLevelAccessor serverLevel, EntitySpawnReason entitySpawnReason, BlockPos pos, RandomSource random) {
        return Config.getGooseSpawnNaturally() && (serverLevel.getBiome(pos).is(Biomes.NETHER_WASTES) || serverLevel.getBiome(pos).is(Biomes.SOUL_SAND_VALLEY) || serverLevel.getBiome(pos).is(Biomes.THE_END)) && serverLevel.getDifficulty() != Difficulty.PEACEFUL && Mob.checkMobSpawnRules(gooseEntityType, serverLevel, entitySpawnReason, pos, random);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        } 
        if (!this.level().isClientSide()) {
            this.setClimbing(this.horizontalCollision);
        }
    }

    static {
        DATA_FLAGS_ID = SynchedEntityData.defineId(GooseEntity.class, EntityDataSerializers.BYTE);
    }
}