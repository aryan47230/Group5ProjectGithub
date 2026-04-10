package com.example.examplemod.entity.ClaudeEnemy;

import com.example.examplemod.cs124uiuc;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class ClaudeEnemyRenderState extends LivingEntityRenderState {
    public AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeOut = 0;

    public AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeOut = 0;

    public ClaudeEnemyRenderState(ClaudeEnemyRenderState state) {
        this.attackAnimationState = state.attackAnimationState;
        this.idleAnimationState = state.idleAnimationState;
    }

    public ClaudeEnemyRenderState() {
    }
}
