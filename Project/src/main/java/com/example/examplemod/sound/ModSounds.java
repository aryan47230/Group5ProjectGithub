package com.example.examplemod.sound;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS;
    public static final Supplier<SoundEvent> HONK;
    public static final Supplier<SoundEvent> HONK_ANGRY;
    public static final Supplier<SoundEvent> GOOSE_HURT;
    public static final Supplier<SoundEvent> GOOSE_DIE;

    public ModSounds() {
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.tryBuild("cs124uiuc", name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    static {
        SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, "cs124uiuc");
        HONK = registerSoundEvent("mob.goose.honk");
        HONK_ANGRY = registerSoundEvent("mob.goose.honk_angry");
        GOOSE_HURT = registerSoundEvent("mob.goose.goose_hurt");
        GOOSE_DIE = registerSoundEvent("mob.goose.goose_die");
    }
}
