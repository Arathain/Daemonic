package net.arathain.daemonic;

import net.arathain.daemonic.registry.ContainmentChamberGenerator;
import net.arathain.daemonic.registry.ContainmentChamberStructureFeature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.arathain.daemonic.effect.TransmutationStatusEffect;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class Daemonic implements ModInitializer {
	public static final StatusEffect TRANSMUTATION = new TransmutationStatusEffect();
	public static final Item CURSED_SOUL = new Item(new FabricItemSettings());
	public static final Block CHAINED_DAEMON_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).lightLevel(10).strength(10.0f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 2).sounds(BlockSoundGroup.CHAIN).build());
	public static final Item CHAINED_DAEMON_BLOCK_ITEM = new BlockItem(CHAINED_DAEMON_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Block CHAIN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(10.0f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 2).sounds(BlockSoundGroup.CHAIN).build());
	public static final Item CHAIN_BLOCK_ITEM = new BlockItem(CHAIN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final StructurePieceType CHAMBER_PIECE = ContainmentChamberGenerator.Piece::new;
	private static final StructureFeature<DefaultFeatureConfig> CHAMBER_STRUCTURE = new ContainmentChamberStructureFeature(DefaultFeatureConfig.CODEC);
	private static final ConfiguredStructureFeature<?, ?> CHAMBER_CONFIGURED = CHAMBER_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("daemonic", "cursed_soul"), CURSED_SOUL);
		Registry.register(Registry.BLOCK, new Identifier("daemonic", "chained_daemon_block"), CHAINED_DAEMON_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("daemonic", "chained_daemon_block"), CHAINED_DAEMON_BLOCK_ITEM);
		Registry.register(Registry.BLOCK, new Identifier("daemonic", "chain_block"), CHAIN_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("daemonic", "chain_block"), CHAIN_BLOCK_ITEM);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("daemonic", "transmutation"), TRANSMUTATION);
		Registry.register(Registry.STRUCTURE_PIECE, new Identifier("daemonic", "chamber_piece"), CHAMBER_PIECE);
		FabricStructureBuilder.create(new Identifier("daemonic", "chamber_structure"), CHAMBER_STRUCTURE)
				.step(GenerationStep.Feature.SURFACE_STRUCTURES)
				.defaultConfig(32, 8, 12345)
				.adjustsSurface()
				.register();

		RegistryKey<ConfiguredStructureFeature<?, ?>> chamberConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
				new Identifier("tutorial", "chamber_structure"));
		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, chamberConfigured.getValue(), CHAMBER_CONFIGURED);
	}

}
