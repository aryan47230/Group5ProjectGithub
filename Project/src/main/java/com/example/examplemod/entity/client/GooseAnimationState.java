package com.example.examplemod.entity.client;

import java.util.function.Consumer;
import net.minecraft.world.entity.AnimationState;

public class GooseAnimationState extends AnimationState {
    public static final int STOPPED = Integer.MIN_VALUE;
    public int startTick = Integer.MIN_VALUE;

    public GooseAnimationState() {
    }

    public void start(int p_216978_) {
        this.startTick = p_216978_;
    }

    public void startIfStopped(int p_216983_) {
        if (!this.isStarted()) {
            this.start(p_216983_);
        }

    }

    public void animateWhen(boolean p_252220_, int p_249486_) {
        if (p_252220_) {
            this.startIfStopped(p_249486_);
        } else {
            this.stop();
        }

    }

    public void stop() {
        this.startTick = Integer.MIN_VALUE;
    }

    public void ifStarted(Consumer<AnimationState> p_216980_) {
        if (this.isStarted()) {
            p_216980_.accept(this);
        }

    }

    public void fastForward(int p_332215_, float p_335055_) {
        if (this.isStarted()) {
            this.startTick -= (int)((float)p_332215_ * p_335055_);
        }

    }

    public long getTimeInMillis(float p_368031_) {
        float f = p_368031_ - (float)this.startTick;
        return (long)(f * 50.0F);
    }

    public boolean isStarted() {
        return this.startTick != Integer.MIN_VALUE;
    }

    public void copyFrom(GooseAnimationState p_369750_) {
        this.startTick = p_369750_.startTick;
    }
}