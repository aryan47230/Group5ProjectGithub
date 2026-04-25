package com.example.examplemod;

import org.slf4j.Logger;

import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.item.UsePortalConsumeEffect;
import com.example.examplemod.screen.ModMenuTypes;
import com.example.examplemod.sound.ModSounds;
import com.example.examplemod.WindCommand;
import com.example.examplemod.WindEventHandler;

import com.mojang.logging.LogUtils;
import java.util.function.Supplier;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModCreativeModeTabs;
import com.example.examplemod.block.entity.ModBlockEntities;
import com.example.examplemod.dimension.ModDimensions;
import com.example.examplemod.entity.ClaudeEnemy.ClaudeEnemy;

@Mod(cs124uiuc.MODID)
public class cs124uiuc {
        public static final String MODID = "cs124uiuc";
        public static final Logger LOGGER = LogUtils.getLogger();
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, MODID);
        public static final DeferredRegister<ConsumeEffect.Type<?>> CONSUME_EFFECT_TYPES = DeferredRegister
                        .create(Registries.CONSUME_EFFECT_TYPE, MODID);
        public static final Supplier<ConsumeEffect.Type<UsePortalConsumeEffect>> USE_PORTAL =
                        CONSUME_EFFECT_TYPES.register("use_portal", () -> new ConsumeEffect.Type<>(
                                        UsePortalConsumeEffect.CODEC, UsePortalConsumeEffect.STREAM_CODEC));

        public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE,
                        MODID);
        public static final Supplier<EntityType<ClaudeEnemy>> CLAUDE_ENEMY = ENTITIES.register("claude_enemy",
                        () -> EntityType.Builder.of(ClaudeEnemy::new, MobCategory.MONSTER)
                                        .sized(0.6f, 1.8f)
                                        .build(ResourceKey.create(
                                                        Registries.ENTITY_TYPE,
                                                        ResourceLocation.fromNamespaceAndPath(MODID, "claude_enemy"))));

        public static final ModelLayerLocation CLAUDE_ENEMY_LAYER = new ModelLayerLocation(
                        ResourceLocation.fromNamespaceAndPath(MODID, "claude_enemy"), "main");

        public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block",
                        p -> p.mapColor(MapColor.STONE));
        public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block",
                        EXAMPLE_BLOCK);
        public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item",
                        p -> p.food(new FoodProperties.Builder()
                                        .alwaysEdible().nutrition(1).saturationModifier(2f).build()));
        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
                        .register("example_tab", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.cs124uiuc"))
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {
                                                output.accept(EXAMPLE_ITEM.get());
                                        }).build());

        public cs124uiuc(IEventBus modEventBus, ModContainer modContainer) {
                modEventBus.addListener(this::commonSetup);
                NeoForge.EVENT_BUS.register(this);
                BLOCKS.register(modEventBus);
                ITEMS.register(modEventBus);
                CREATIVE_MODE_TABS.register(modEventBus);
                CONSUME_EFFECT_TYPES.register(modEventBus);

                ModItems.register(modEventBus);
                ModBlocks.register(modEventBus);
                ModBlockEntities.register(modEventBus);
                ModCreativeModeTabs.register(modEventBus);
                ModEntities.register(modEventBus);
                ModSounds.register(modEventBus);
                ModMenuTypes.register(modEventBus);
                PacketHandler.register(modEventBus);

                modEventBus.addListener(this::addCreative);
                modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
                ModDimensions.register();
                ENTITIES.register(modEventBus);
                NeoForge.EVENT_BUS.register(new WindEventHandler());
        }

        private void commonSetup(FMLCommonSetupEvent event) {
                LOGGER.info("HELLO FROM COMMON SETUP");

                if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
                        LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
                }

                LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

                Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
        }

        private void addCreative(BuildCreativeModeTabContentsEvent event) {
                if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
                        event.accept(EXAMPLE_BLOCK_ITEM);
                } else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
                        event.accept((ItemLike) ModItems.GOOSE_SPAWN_EGG.get());
                }
        }

        @SubscribeEvent
        public void onServerStarting(ServerStartingEvent event) {
                LOGGER.info("HELLO from server starting");
        }

        @SubscribeEvent
        public void onRegisterCommands(net.neoforged.neoforge.event.RegisterCommandsEvent event) {
                WindCommand.register(event.getDispatcher());
        }
}