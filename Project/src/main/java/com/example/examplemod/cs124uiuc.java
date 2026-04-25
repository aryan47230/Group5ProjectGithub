package com.example.examplemod;

import org.slf4j.Logger;

import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.entity.client.GooseModel;
import com.example.examplemod.entity.client.GooseRenderer;
import com.example.examplemod.entity.custom.GooseEntity;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.item.UsePortalConsumeEffect;
import com.example.examplemod.sound.ModSounds;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModCreativeModeTabs;
import com.example.examplemod.block.entity.ModBlockEntities;
import com.example.examplemod.dimension.ModDimensions;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemy;
import com.mojang.logging.LogUtils;
import java.util.function.Supplier;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(cs124uiuc.MODID)
public class cs124uiuc {
        public static final String MODID = "cs124uiuc";
        public static final Logger LOGGER = LogUtils.getLogger();
        // Create a Deferred Register to hold Blocks which will all be registered under
        // the "cs124uiuc" namespace
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
        // Create a Deferred Register to hold Items which will all be registered under
        // the "cs124uiuc" namespace
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
        // Create a Deferred Register to hold CreativeModeTabs which will all be
        // registered under the "cs124uiuc" namespace
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, MODID);
        // public static final DeferredRegister<ConsumeEffect.Type<?>>
        // CONSUME_EFFECT_TYPES;
        public static final DeferredRegister<ConsumeEffect.Type<?>> CONSUME_EFFECT_TYPES = DeferredRegister
                        .create(Registries.CONSUME_EFFECT_TYPE, MODID);
        public static final Supplier<ConsumeEffect.Type<UsePortalConsumeEffect>> USE_PORTAL =
                        // CONSUME_EFFECT_TYPES.register("use_portal", () -> new
                        // ConsumeEffect.Type<>(UsePortalConsumeEffect::new));

                        CONSUME_EFFECT_TYPES.register("use_portal", () -> new ConsumeEffect.Type<>(
                                        UsePortalConsumeEffect.CODEC, UsePortalConsumeEffect.STREAM_CODEC));

        public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE,
                        MODID);
        public static final Supplier<EntityType<ClaudeEnemy>> CLAUDE_ENEMY = ENTITIES.register("claude_enemy",
                        () -> EntityType.Builder.of(ClaudeEnemy::new, MobCategory.MONSTER)
                                        .sized(0.6f, 1.8f) // Hitbox size
                                        .build(ResourceKey.create(
                                                        Registries.ENTITY_TYPE,
                                                        ResourceLocation.fromNamespaceAndPath(MODID, "claude_enemy"))));

        public static final ModelLayerLocation CLAUDE_ENEMY_LAYER = new ModelLayerLocation(
                        ResourceLocation.fromNamespaceAndPath(MODID, "claude_enemy"), "main");

        // Creates a new Block with the id "cs124uiuc:example_block", combining the
        // namespace and path
        public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block",
                        p -> p.mapColor(MapColor.STONE));
        // Creates a new BlockItem with the id "cs124uiuc:example_block", combining the
        // namespace and path
        public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block",
                        EXAMPLE_BLOCK);

        // Creates a new food item with the id "cs124uiuc:example_id", nutrition 1 and
        // saturation 2
        public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item",
                        p -> p.food(new FoodProperties.Builder()
                                        .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

        // Creates a creative tab with the id "cs124uiuc:example_tab" for the example
        // item, that is placed after the combat tab
        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
                        .register("example_tab", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.cs124uiuc")) // The language key for
                                                                                              // the title of your
                                                                                              // CreativeModeTab
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {
                                                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab.
                                                                                   // For your own tabs, this method is
                                                                                   // preferred over the event
                                        }).build());

        // The constructor for the mod class is the first code that is run when your mod
        // is loaded.
        // FML will recognize some parameter types like IEventBus or ModContainer and
        // pass them in automatically.
        public cs124uiuc(IEventBus modEventBus, ModContainer modContainer) {
                // Register the commonSetup method for modloading
                modEventBus.addListener(this::commonSetup);
                NeoForge.EVENT_BUS.register(this);
                BLOCKS.register(modEventBus);
                ITEMS.register(modEventBus);
                CREATIVE_MODE_TABS.register(modEventBus);
                CONSUME_EFFECT_TYPES.register(modEventBus);

                // Register ourselves for server and other game events we are interested in.
                // Note that this is necessary if and only if we want *this* class (cs124uiuc)
                // to respond directly to events.
                // Do not add this line if there are no @SubscribeEvent-annotated functions in
                // this class, like onServerStarting() below.
                // NeoForge.EVENT_BUS.register(this);

                ModBlocks.register(modEventBus);
                ModItems.register(modEventBus);
                ModCreativeModeTabs.register(modEventBus);
                ModEntities.register(modEventBus);
                ModSounds.register(modEventBus);

                // Register the item to a creative tab
                modEventBus.addListener(this::addCreative);

                // Register our mod's ModConfigSpec so that FML can create and load the config
                // file for us
                modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

                ModDimensions.register();

                ENTITIES.register(modEventBus);

                // NeoForge.EVENT_BUS.register(new WindHandler());
                NeoForge.EVENT_BUS.register(new WindEventHandler());
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(EXAMPLE_BLOCK_ITEM);
        } else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept((ItemLike) ModItems.GOOSE_SPAWN_EGG.get());
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onRegisterCommands(net.neoforged.neoforge.event.RegisterCommandsEvent event) {
        WindCommand.register(event.getDispatcher());
    }

    /*
    @EventBusSubscriber(modid = cs124uiuc.MODID, bus = EventBusSubscriber.Bus.Mod, value=Dist.client)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.GOOSE.get(), GooseRenderer::new);
        }

        @SubscribeEvent
        public void onRegisterCommands(net.neoforged.neoforge.event.RegisterCommandsEvent event) {
                WindCommand.register(event.getDispatcher());
        }
        /*
         * @EventBusSubscriber(modid = cs124uiuc.MODID, bus =
         * EventBusSubscriber.Bus.Mod, value=Dist.client)
         * public static class ClientModEvents {
         * 
         * @SubscribeEvent
         * public static void onClientSetup(FMLClientSetupEvent event) {
         * EntityRenderers.register(ModEntities.GOOSE.get(), GooseRenderer::new);
         * }
         * }
         * 
         * @SubscribeEvent
         * public static void
         * registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
         * event.registerEntityRenderer((EntityType)GooseEntity, (m) -> new
         * GooseRenderer(m));
         * }
         * 
         * @SubscribeEvent
         * public static void
         * registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
         * {
         * event.registerLayerDefinition(GooseModel.LAYER_LOCATION,
         * GooseModel::createBodyLayer);
         * }
         */
}
