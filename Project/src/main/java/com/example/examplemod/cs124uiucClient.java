package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;

import static com.example.examplemod.cs124uiuc.CLAUDE_ENEMY;
import static com.example.examplemod.cs124uiuc.CLAUDE_ENEMY_LAYER;

import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemy;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemy;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemyModel;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemyRenderer;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = cs124uiuc.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods
// in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = cs124uiuc.MODID, value = Dist.CLIENT)
public class cs124uiucClient {
    public cs124uiucClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your
        // mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json
        // file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        cs124uiuc.LOGGER.info("HELLO FROM CLIENT SETUP");
        cs124uiuc.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CLAUDE_ENEMY.get(), ClaudeEnemyRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CLAUDE_ENEMY_LAYER, ClaudeEnemyModel::createBodyLayer);
    }

    @SubscribeEvent // on the mod event bus
    public static void createDefaultAttributes(EntityAttributeCreationEvent event) {
        event.put(
                // Your entity type.
                CLAUDE_ENEMY.get(),
                // An AttributeSupplier. This is typically created by calling
                // LivingEntity#createLivingAttributes,
                // setting your values on it, and calling #build. You can also create the
                // AttributeSupplier from scratch
                // if you want, see the source of LivingEntity#createLivingAttributes for an
                // example.
                ClaudeEnemy.createAttributes()
                        .build());
    }
}
