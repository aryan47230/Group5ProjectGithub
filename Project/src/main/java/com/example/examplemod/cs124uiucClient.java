package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import static com.example.examplemod.cs124uiuc.CLAUDE_ENEMY;
import static com.example.examplemod.cs124uiuc.CLAUDE_ENEMY_LAYER;

import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemy;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemyModel;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemyRenderer;
import com.example.examplemod.screen.ModMenuTypes;
import com.example.examplemod.screen.custom.ComputerScreen;

@Mod(value = cs124uiuc.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = cs124uiuc.MODID, value = Dist.CLIENT)
public class cs124uiucClient {
    public cs124uiucClient(ModContainer container, IEventBus modEventBus) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your
        // mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json
        // 
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener((RegisterMenuScreensEvent event) ->
            event.register(ModMenuTypes.COMPUTER_MENU.get(), ComputerScreen::new));
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CLAUDE_ENEMY.get(), ClaudeEnemyRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CLAUDE_ENEMY_LAYER, ClaudeEnemyModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void createDefaultAttributes(EntityAttributeCreationEvent event) {
        event.put(
                CLAUDE_ENEMY.get(),
                ClaudeEnemy.createAttributes()
                        .build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                CLAUDE_ENEMY.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, random) -> true,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        
    }
}
