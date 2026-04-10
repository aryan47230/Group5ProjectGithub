package com.example.examplemod.entity.ClaudeEnemy;

import javax.swing.plaf.metal.MetalLabelUI;
import javax.swing.text.html.parser.Entity;

import com.example.examplemod.cs124uiuc;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ClaudeEnemy extends Monster {

    public static final EntityDataAccessor<Boolean> isAttackingDataAccessor = SynchedEntityData.defineId(
            ClaudeEnemy.class,
            EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> attackTimer = SynchedEntityData.defineId(ClaudeEnemy.class,
            EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> isIdleDataAccessor = SynchedEntityData.defineId(
            ClaudeEnemy.class,
            EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> idleTimer = SynchedEntityData.defineId(ClaudeEnemy.class,
            EntityDataSerializers.INT);

    public ClaudeEnemy(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        final ClaudeEnemy entity = this;
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false) {
            @Override
            public boolean canPerformAttack(LivingEntity target) {
                boolean canAttack = super.canPerformAttack(target);
                if (canAttack) {
                    entity.getEntityData().set(isAttackingDataAccessor, true);
                    entity.getEntityData().set(attackTimer, 20);
                }
                return canAttack;
            }
        });
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal(this, LivingEntity.class, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this) {

            @Override
            public void start() {
                super.start();
                entity.getEntityData().set(isIdleDataAccessor, true);
                entity.getEntityData().set(idleTimer, 60);
            }
        });
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            // System.out.println("attackTimer: " + attackTimer);
            // System.out.println("idleTimer: " + idleTimer);
            setUpAnimations();
        }
    }

    private void setUpAnimations() {
        if (this.getEntityData().get(attackTimer) > 0) {
            this.getEntityData().set(attackTimer, this.getEntityData().get(attackTimer) - 1);
        } else {
            this.getEntityData().set(isAttackingDataAccessor, false);
        }
        if (this.getEntityData().get(idleTimer) > 0) {
            this.getEntityData().set(idleTimer, this.getEntityData().get(idleTimer) - 1);
        } else {
            this.getEntityData().set(isIdleDataAccessor, false);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(isAttackingDataAccessor, false);
        builder.define(isIdleDataAccessor, false);
        builder.define(attackTimer, 0);
        builder.define(idleTimer, 0);
    }
}
