package com.gkoliver.nwis.core.register;

import java.util.ArrayList;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.common.block.crop.ECropTypes;
import com.gkoliver.nwis.common.block.crop.FakeAttachedBlock;
import com.gkoliver.nwis.common.block.crop.FakeBeetrootBlock;
import com.gkoliver.nwis.common.block.crop.FakeCocoaBeanBlock;
import com.gkoliver.nwis.common.block.crop.FakeGrowableBlock;
import com.gkoliver.nwis.common.block.other.ImposterStationBlock;
import com.gkoliver.nwis.common.block.other.NWISBlock;
import com.gkoliver.nwis.common.block.other.NWISNorthableBlock;
import com.gkoliver.nwis.common.block.other.NWISOrientableBlock;
import com.gkoliver.nwis.common.block.other.NWISSaplingBlock;
import com.gkoliver.nwis.common.block.other.NWISWaterLogBlock;
import com.gkoliver.nwis.common.block.other.RestrainedDillutedPortalBlock;
import com.gkoliver.nwis.common.block.other.RestrainedVoidBlock;
import com.gkoliver.nwis.common.block.other.SemiInvisibleBlock;
import com.gkoliver.nwis.common.block.other.SemiInvisibleNorthableBlock;
import com.gkoliver.nwis.common.block.other.VoidBlock;
import com.gkoliver.nwis.common.block.vegitation.NetherRootStuff;
import com.gkoliver.nwis.common.block.vegitation.PassionVineBlock;
import com.gkoliver.nwis.common.block.vegitation.corals.CoralWallFanBlock;
import com.gkoliver.nwis.common.block.vegitation.corals.ECoralType;
import com.gkoliver.nwis.common.block.vegitation.corals.CoralFanBlock;
import com.gkoliver.nwis.common.block.vegitation.kelp.KelpBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.CropSproutBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.DoubleDoubleCropBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.EDoubleCropType;
import com.gkoliver.nwis.common.block.vegitation.swex.SingleDoubleCropBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.pickelreedtype.PickelReedSingleBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.pickelreedtype.PickelreedDoubleBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.rice.RiceDoubleSingleBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.rice.RiceSingleSingleBlock;
import com.gkoliver.nwis.common.block.vegitation.vanilla.ChorusFruitBlock;
import com.gkoliver.nwis.common.block.vegitation.vanilla.EOrientables;
import com.gkoliver.nwis.common.block.vegitation.vanilla.ETallTypes;
import com.gkoliver.nwis.common.block.vegitation.vanilla.FakeVineBlock;
import com.gkoliver.nwis.common.block.vegitation.vanilla.NWISBambooBlock;
import com.gkoliver.nwis.common.block.vegitation.vanilla.NewChorusPlantBlock;
import com.gkoliver.nwis.common.block.vegitation.vanilla.OrientableVeggies;
import com.gkoliver.nwis.common.block.vegitation.vanilla.TallOrientableBlock;
import com.gkoliver.nwis.common.gui.ImposterContainer;
import com.gkoliver.nwis.core.event.ClientEvents;

import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NWISBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			NotWhatItSeems.MODID);
	static final public String ID = ""; 
	/**
	 * Generates a block with no creative tab.
	 * 
	 * @return
	 */
	public static ArrayList<Block> CUTOUTS = new ArrayList<Block>();
	static boolean debug = false;
	public static RegistryObject<Block> genBlock(String id, Block block) {
		
		BlockItem item = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
		NWISItems.ITEMS.register(id, () -> item);
		return NWISBlocks.BLOCKS.register(id, () -> block);
	}
	public static RegistryObject<Block> genBlock(String id, Block block, int color) {
		
		BlockItem item = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
		NWISItems.ITEMS.register(id, () -> item);
		if (color!=0) {
			ClientEvents.COLOR_MAPS.put(item, color);
		}
		return NWISBlocks.BLOCKS.register(id, () -> block);
	}
	public static RegistryObject<Block> genBlock(String id, Block block, int color, String modid) {
		Item.Properties prop = new Item.Properties();
		if (ModList.get().isLoaded(modid) && modid!="???!!!" || debug) {
			prop.group(ItemGroup.SEARCH);
		}
		BlockItem item = new BlockItem(block, prop);
		NWISItems.ITEMS.register(id, () -> item);
		if (color!=0) {
			ClientEvents.COLOR_MAPS.put(item, color);
		}
		return NWISBlocks.BLOCKS.register(id, () -> block);
	}
	public static RegistryObject<Block> genBlock2(String id, Block block) {
		BlockItem item = new BlockItem(block, new Item.Properties());
		NWISItems.ITEMS.register(id, () -> item);
		CUTOUTS.add(block);
		return NWISBlocks.BLOCKS.register(id, () -> block);
	}
	public static RegistryObject<Block> genBlock2(String id, Block block, int color) {
		BlockItem item = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
		NWISItems.ITEMS.register(id, () -> item);
		CUTOUTS.add(block);
		if (color!=0) {
			ClientEvents.COLOR_MAPS.put(item, color);
		}
		return NWISBlocks.BLOCKS.register(id, () -> block);
	}
	public static RegistryObject<Block> genBlock2(String id, Block block, int color, String modid) {
		Item.Properties prop = new Item.Properties();
		if (ModList.get().isLoaded(modid) || debug) {
			prop.group(ItemGroup.SEARCH);
		}
		BlockItem item = new BlockItem(block, prop);
		NWISItems.ITEMS.register(id, () -> item);
		CUTOUTS.add(block);
		if (color!=0) {
			ClientEvents.COLOR_MAPS.put(item, color);
		}
		return NWISBlocks.BLOCKS.register(id, () -> block);
	}

	// 0: Alive Block
	// 1: Dead Block
	// 2: Alive Coral
	// 3: Dead Coral
	// 4: Alive Coral Fan
	// 5: Dead Coral Fan
	// 6: Alive Coral Fan (Wall)
	// 7: Dead Coral Fan (Wall)
	public static ArrayList<RegistryObject<Block>> genCoral(String name, boolean ua, int color) {
		ArrayList<RegistryObject<Block>> tbr = new ArrayList<RegistryObject<Block>>();
		NWISBlock alive_block = new NWISBlock(PROP_CORAL);
		NWISBlock dead_block = new NWISBlock(PROP_CORAL);
		CoralFanBlock alive = new CoralFanBlock(PROP_CORAL_T, ECoralType.SMALL, null);
		CoralFanBlock dead = new CoralFanBlock(PROP_CORAL_T, ECoralType.SMALL, null);
		CoralWallFanBlock alive_wall_fan = new CoralWallFanBlock(PROP_CORAL_T);
		CoralWallFanBlock dead_wall_fan = new CoralWallFanBlock(PROP_CORAL_T);
		CoralFanBlock alive_fan = new CoralFanBlock(PROP_CORAL_T, ECoralType.FAN, alive_wall_fan);
		CoralFanBlock dead_fan = new CoralFanBlock(PROP_CORAL_T, ECoralType.FAN, dead_wall_fan);
		ArrayList<Block> blocky = new ArrayList<Block>() {
			private static final long serialVersionUID = 1L;
			{
				add(alive_block);
				add(dead_block);
				add(alive);
				add(dead);
				add(alive_fan);
				add(dead_fan);
			}
		};
		
		ArrayList<String> stringy = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add(name + "_coral_block");
				add("dead_" + name + "_coral_block");
				add(name + "_coral");
				add("dead_" + name + "_coral");
				add(name + "_coral_fan");
				add("dead_" + name + "_coral_fan");
			}
		};
		if (ua) {
			ImposterContainer.ua_corals.add(stringy);
		} else {
			ImposterContainer.vanilla_corals.add(stringy);
		}
		tbr.add(0, BLOCKS.register(name + "_coral_block", () -> alive_block));
		tbr.add(1, BLOCKS.register("dead_" + name + "_coral_block", () -> dead_block));
		tbr.add(2, BLOCKS.register(name + "_coral", () -> alive));
		tbr.add(3, BLOCKS.register("dead_" + name + "_coral", () -> dead));
		tbr.add(4, BLOCKS.register(name + "_coral_fan", () -> alive_fan));
		tbr.add(5, BLOCKS.register("dead_" + name + "_coral_fan", () -> dead_fan));
		tbr.add(6, BLOCKS.register(name + "_coral_wall_fan", () -> alive_wall_fan));
		tbr.add(7, BLOCKS.register("dead_" + name + "_coral_wall_fan", () -> dead_wall_fan));
		for (int i = 0; i < blocky.size(); i++) {
			Block block = blocky.get(i);
			BlockItem bi;
			Item.Properties prop = new Item.Properties();
			if (ua) {
				if (ModList.get().isLoaded("upgrade_aquatic") || debug) {
					prop.group(ItemGroup.SEARCH);
				}
			} else {
				prop.group(ItemGroup.SEARCH);
			}
			bi = new BlockItem(block, prop);
			if (stringy.get(i).contains("dead")) {
				if (stringy.get(i).contains("prismarine")) {
					ClientEvents.COLOR_MAPS.put(bi, 0xA29281);
				} else {
					ClientEvents.COLOR_MAPS.put(bi, 0x808080);
				}
			} else {
				ClientEvents.COLOR_MAPS.put(bi, color);
			}
			
			
			NWISItems.ITEMS.register(stringy.get(i), () -> bi);
		}
		CUTOUTS.add(alive);
		CUTOUTS.add(dead);
		CUTOUTS.add(alive_fan);
		CUTOUTS.add(dead_fan);
		CUTOUTS.add(alive_wall_fan);
		CUTOUTS.add(dead_wall_fan);
		return tbr;

	}
	
	public static final Block.Properties PROP_CROPS = Block.Properties.create(Material.PLANTS).doesNotBlockMovement().notSolid().sound(SoundType.PLANT);
	public static final Block.Properties PROP_VOID = Block.Properties.create(Material.PORTAL).notSolid().hardnessAndResistance(2.0F, 3.0F);
	public static final Block.Properties PROP_SAPLING = Block.Properties.create(Material.PLANTS).doesNotBlockMovement()
			.notSolid().sound(SoundType.PLANT);
	public static final Block.Properties PROP_SOIL = Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).hardnessAndResistance(0.5F);//.harvestTool(ToolType.SHOVEL);
	public static final Block.Properties PROP_POISE = Block.Properties.create(Material.ORGANIC).sound(SoundType.BAMBOO).hardnessAndResistance(0.5F).notSolid();
	public static final Block.Properties PROP_MUSH = Block.Properties.create(Material.ORGANIC).sound(SoundType.WOOD).hardnessAndResistance(0.2F);//.(ToolType.AXE);
	public static final Block.Properties PROP_GLOWMUSH = Block.Properties.create(Material.ORGANIC).sound(SoundType.WOOD).notSolid().hardnessAndResistance(0.2F);//.harvestTool(ToolType.AXE);
	public static final Block.Properties PROP_STATION = Block.Properties.create(Material.ROCK).sound(SoundType.METAL).hardnessAndResistance(1.5F, 3.0F);
	public static final Block.Properties PROP_CORAL = Block.Properties.create(Material.CORAL).sound(SoundType.CORAL).hardnessAndResistance(1.5F, 6.0F);
	public static final Block.Properties PROP_CORAL_T = Block.Properties.create(Material.CORAL).sound(SoundType.CORAL).hardnessAndResistance(1.5F, 6.0F)
			.doesNotBlockMovement().notSolid();
	public static final Block.Properties PROP_NYLIUM = Block.Properties.create(Material.WOOD).sound(SoundType.field_235579_A_).hardnessAndResistance(1.0F);
	public static final Block.Properties PROP_FUNGUS = Block.Properties.create(Material.WOOD).sound(SoundType.field_235580_B_).doesNotBlockMovement().notSolid();
	public static final AbstractBlock.Properties PROP_SEMISOLIDS = AbstractBlock.Properties.create(Material.IRON).sound(SoundType.field_235597_S_).notSolid();
	public static final AbstractBlock.Properties PROP_TORCH = AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).notSolid().doesNotBlockMovement();
	public static final AbstractBlock.Properties PROP_ANCIENTDEBRIS = AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.field_235595_Q_);


	public static final Block.Properties PROP_CHORUS_FRUIT = Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).notSolid().hardnessAndResistance(0.4F);//(ToolType.AXE);
	private static final Properties PROP_VOID_SEMI = Block.Properties.create(Material.PORTAL).notSolid().doesNotBlockMovement().hardnessAndResistance(2.0F, 3.0F);
	private static final Properties PROP_TALL_GRASS = Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().sound(SoundType.PLANT);
	public static final RegistryObject<Block> FAKE_CARROTS = genBlock2("fake_carrot",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.CARROT), 0xFF8E09);
	public static final RegistryObject<Block> FAKE_POTATO = genBlock2("fake_potato",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.POTATO), 0xD9AA51);
	public static final RegistryObject<Block> FAKE_WHEAT = genBlock2("fake_wheat",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.WHEAT), 0xDCBB65);
	public static final RegistryObject<Block> FAKE_BEETROOT = genBlock2("fake_beetroot",
			new FakeBeetrootBlock(PROP_CROPS), 0xA4272C);
	public static final RegistryObject<Block> FAKE_MELON_STEM = genBlock2("fake_melon_stem",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.MELON_STEM), 0x2A220B);
	public static final RegistryObject<Block> FAKE_PUMPKIN_STEM = genBlock2("fake_pumpkin_stem",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.PUMPKIN_STEM), 0xCBC68D);
	public static final RegistryObject<Block> FAKE_COCOA_BEAN = genBlock2("fake_cocoa_beans",
			new FakeCocoaBeanBlock(PROP_MUSH), 0x704425);
	public static final RegistryObject<Block> FAKE_PUMPKIN_STEM_ATTACHED = genBlock2("fake_pumpkin_stem_attached",
			new FakeAttachedBlock(PROP_CROPS, ECropTypes.PUMPKIN_STEM), 0, "???!!!");
	public static final RegistryObject<Block> FAKE_MELON_STEM_ATTACHED = genBlock2("fake_melon_stem_attached",
			new FakeAttachedBlock(PROP_CROPS, ECropTypes.MELON_STEM), 0, "???!!!");
	
	//Void Blocks
	public static final RegistryObject<Block> VOID_BLOCK = genBlock("void_block", new VoidBlock(PROP_VOID), 0);
	public static final RegistryObject<Block> VOID_BLOCK_SEMISOLID = genBlock("void_block_semi", new VoidBlock(PROP_VOID_SEMI), 0);
	public static final RegistryObject<Block> RESTRAINED_VOID_BLOCK = genBlock("restrained_void_block", new RestrainedVoidBlock(PROP_VOID), 0);
	public static final RegistryObject<Block> RESTRAINED_VOID_BLOCK_SEMI = genBlock("restrained_void_block_semi", new RestrainedVoidBlock(PROP_VOID_SEMI), 0);
	
	
	//Dilluted Void Blocks
	public static final RegistryObject<Block> DILLUTED_VOID_BLOCK = genBlock("dilluted_void_block",
			new SemiInvisibleBlock(PROP_VOID), 0);
	public static final RegistryObject<Block> DILLUTED_VOID_BLOCK_SEMISOLID = genBlock("dilluted_void_block_semi",
			new SemiInvisibleBlock(PROP_VOID_SEMI), 0);
	public static final RegistryObject<Block> RESTRAINED_DILLUTED_VOID_BLOCK = genBlock(
			"restrained_dilluted_void_block", new RestrainedDillutedPortalBlock(PROP_VOID), 0);
	public static final RegistryObject<Block> RESTRAINED_DILLUTED_VOID_BLOCK_SEMI = genBlock(
			"restrained_dilluted_void_block_semi", new RestrainedDillutedPortalBlock(PROP_VOID_SEMI), 0);
	
	public static final RegistryObject<Block> FAKE_OAK_SAPLING = genBlock2("fake_oak_sapling",
			new NWISSaplingBlock(PROP_SAPLING), 0x408F2F);
	public static final RegistryObject<Block> FAKE_BIRCH_SAPLING = genBlock2("fake_birch_sapling",
			new NWISSaplingBlock(PROP_SAPLING), 0x6C9E38);
	public static final RegistryObject<Block> FAKE_SPRUCE_SAPLING = genBlock2("fake_spruce_sapling",
			new NWISSaplingBlock(PROP_SAPLING), 0x2E492E);
	public static final RegistryObject<Block> FAKE_JUNGLE_SAPLING = genBlock2("fake_jungle_sapling",
			new NWISSaplingBlock(PROP_SAPLING), 0x2B4A0C);
	public static final RegistryObject<Block> FAKE_ACACIA_SAPLING = genBlock2("fake_acacia_sapling",
			new NWISSaplingBlock(PROP_SAPLING), 0x7E9821);
	public static final RegistryObject<Block> FAKE_DARK_OAK_SAPLING = genBlock2("fake_dark_oak_sapling",
			new NWISSaplingBlock(PROP_SAPLING), 0x105210);
	public static final RegistryObject<Block> FAKE_NETHER_WART = genBlock2("fake_nether_wart",
			new NWISSaplingBlock(PROP_CROPS), 0x760001);

	public static final RegistryObject<Block> FAKE_VINE = genBlock2("fake_vine", new FakeVineBlock(PROP_CROPS), 0x255E0D);

	public static final RegistryObject<Block> FAKE_MUSHOOM_1 = genBlock2("fake_mushroom_red",
			new OrientableVeggies(EOrientables.MUSHROOM, PROP_SAPLING), 0xFE2A2A);
	public static final RegistryObject<Block> FAKE_MUSHOOM_2 = genBlock2("fake_mushroom_brown",
			new OrientableVeggies(EOrientables.MUSHROOM, PROP_SAPLING), 0x916D55);

	// Grass Blocks
	public static final RegistryObject<Block> STATIC_DIRT = genBlock("fake_dirt", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_GRASS = genBlock("fake_grass", new NWISOrientableBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_MYCELIUM = genBlock("fake_mycelium", new NWISOrientableBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_PODZOL = genBlock("fake_podzol", new NWISOrientableBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_PATH = genBlock("fake_path", new NWISOrientableBlock(PROP_SOIL));
	
	public static final RegistryObject<Block> STATIC_GRASS_A = genBlock("fake_grass_a", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_MYCELIUM_A = genBlock("fake_mycelium_a", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_PODZOL_A = genBlock("fake_podzol_a", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_PATH_A = genBlock("fake_path_a", new NWISBlock(PROP_SOIL));
	
	public static final RegistryObject<Block> IMPOSTER_STATION = genBlock("imposter_station",
			new ImposterStationBlock(PROP_STATION), 0);
	public static final RegistryObject<Block> SPECIAL_MUSHROOM = genBlock("fake_brown_mushroom_block",
			new NWISNorthableBlock(PROP_MUSH), 0);
	public static final RegistryObject<Block> SPECIAL_MUSHROOM_R = genBlock("fake_red_mushroom_block",
			new NWISNorthableBlock(PROP_MUSH), 0);
	public static final RegistryObject<Block> FAKE_MUSHROOM_STEM = genBlock("fake_mushroom_stem",
			new NWISNorthableBlock(PROP_MUSH), 0);
	public static final RegistryObject<Block> FAKE_CHORUS = genBlock("fake_chorus_fruit",
			new NewChorusPlantBlock(PROP_CHORUS_FRUIT), 0);
	public static final RegistryObject<Block> FAKE_CHORUS_FLOWER = genBlock("fake_chorus_flower",
			new ChorusFruitBlock(PROP_CHORUS_FRUIT), 0);
	public static final RegistryObject<Block> FAKE_DRAGON_EGG = genBlock("fake_dragon_egg",
			new NWISWaterLogBlock(PROP_CHORUS_FRUIT), 0);
	public static final RegistryObject<Block> FAKE_BAMBOO = genBlock2("fake_bamboo",
			new NWISBambooBlock(PROP_POISE), 0x467301);
	
	// Endergetic
	public static final RegistryObject<Block> STATIC_POSIMOSS = genBlock("fake_posimoss", new NWISOrientableBlock(PROP_SOIL), 0, "endergetic");
	public static final RegistryObject<Block> STATIC_POSIMOSS_TALL = genBlock2("fake_posimoss_tall", new NWISOrientableBlock(PROP_TALL_GRASS), 0x4A2A4A, "endergetic");
	public static final RegistryObject<Block> STATIC_POSIMOSS_TALL_DOUBLE = genBlock2("fake_posimoss_tall_double", new TallOrientableBlock(ETallTypes.TALL_GRASS, PROP_TALL_GRASS), 0xCF40B5, "endergetic");
	public static final RegistryObject<Block> STATIC_POSIMOSS_A = genBlock("fake_posimoss_a", new NWISBlock(PROP_SOIL), 0, "endergetic");
	public static final RegistryObject<Block> STATIC_POSIMOSS_EUMUS = genBlock("fake_posimoss_eumus", new NWISOrientableBlock(PROP_SOIL), 0, "endergetic");
	public static final RegistryObject<Block> STATIC_POISE_CLUSTER = genBlock("fake_poise_cluster", new SemiInvisibleBlock(PROP_POISE), 0, "endergetic");
	
	// Quark: Glowing Caves
	public static final RegistryObject<Block> FAKE_GLOWCELIUM = genBlock("fake_glowcelium", new NWISOrientableBlock(PROP_MUSH), 0, "quark");
	public static final RegistryObject<Block> FAKE_GLOWCELIUM_A = genBlock("fake_glowcelium_a",
			new NWISBlock(PROP_MUSH), 0, "quark");
	public static final RegistryObject<Block> FAKE_BIG_GLOWSHROOM = genBlock("fake_big_glowshroom",
			new SemiInvisibleNorthableBlock(PROP_GLOWMUSH), 0, "quark");
	public static final RegistryObject<Block> FAKE_BIG_GLOWSHROOM_STEM = genBlock("fake_big_glowshroom_stem",
			new SemiInvisibleNorthableBlock(PROP_GLOWMUSH), 0, "quark");
	public static final RegistryObject<Block> FAKE_GLOWSHROOM = genBlock2("fake_glowshroom", new OrientableVeggies(EOrientables.MUSHROOM,PROP_SAPLING), 0x6BD8F8, "quark");
	public static final RegistryObject<Block> FAKE_CAVE_ROOTS = genBlock2("fake_cave_roots", new FakeVineBlock(PROP_CROPS), 0xCBA365, "quark");
	// Sapling
	public static final RegistryObject<Block> FROSTY_SAPLING = genBlock2("fake_qsap_frosty", new NWISSaplingBlock(PROP_SAPLING), 0x3C95B7, "quark");
	public static final RegistryObject<Block> SERENE_SAPLING = genBlock2("fake_qsap_serene", new NWISSaplingBlock(PROP_SAPLING), 0x9D7EAA, "quark");
	public static final RegistryObject<Block> WARM_SAPLING = genBlock2("fake_qsap_warm", new NWISSaplingBlock(PROP_SAPLING), 0xE18D2B, "quark");
	public static final RegistryObject<Block> SUNNY_SAPLING = genBlock2("fake_qsap_sunny", new NWISSaplingBlock(PROP_SAPLING), 0xC5BC53, "quark");
	public static final RegistryObject<Block> SWEET_SAPLING = genBlock2("fake_qsap_sweet", new NWISSaplingBlock(PROP_SAPLING), 0xE5B7D3, "quark");
	// Autuminity
	public static final RegistryObject<Block> MAPLE_SAPLING = genBlock2("fake_maple_sapling", new NWISSaplingBlock(PROP_SAPLING), 0x31621B, "autumnity");
	public static final RegistryObject<Block> MAPLE_SAPLING_RED = genBlock2("fake_maple_sapling_red",
			new NWISSaplingBlock(PROP_SAPLING), 0xBA420E, "autumnity");
	public static final RegistryObject<Block> MAPLE_SAPLING_YELLOW = genBlock2("fake_maple_sapling_yellow",
			new NWISSaplingBlock(PROP_SAPLING), 0x977200, "autumnity");
	public static final RegistryObject<Block> MAPLE_SAPLING_ORANGE = genBlock2("fake_maple_sapling_orange",
			new NWISSaplingBlock(PROP_SAPLING), 0x924D17, "autumnity");
	// Atmospheric
	public static final RegistryObject<Block> PASSION_VINE = genBlock2("fake_passion_vine", new PassionVineBlock(PROP_CROPS), 0x7A0145, "atmospheric");
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = genBlock2("fake_rosewood_sapling", new NWISSaplingBlock(PROP_SAPLING), 0x437224, "atmospheric");
	public static final RegistryObject<Block> YUCCA_SAPLING = genBlock2("fake_yucca_sapling", new NWISSaplingBlock(PROP_SAPLING), 0x85A143, "atmospheric");
	public static final RegistryObject<Block> ASPEN_SAPLING = genBlock2("fake_aspen_sapling", new NWISSaplingBlock(PROP_SAPLING), 0xFFD556, "atmospheric");
	public static final RegistryObject<Block> KOUSA_SAPLING = genBlock2("fake_kousa_sapling", new NWISSaplingBlock(PROP_SAPLING), 0xBBDDD1, "atmospheric");
	// Bloomful
	public static final RegistryObject<Block> WISTERIA_PINK = genBlock2("fake_wisteria_pink", new NWISSaplingBlock(PROP_SAPLING), 0xf29bbb, "environmental");
	public static final RegistryObject<Block> WISTERIA_BLUE = genBlock2("fake_wisteria_blue", new NWISSaplingBlock(PROP_SAPLING), 0x6c95c9, "environmental");
	public static final RegistryObject<Block> WISTERIA_PURPLE = genBlock2("fake_wisteria_purple", new NWISSaplingBlock(PROP_SAPLING), 0x9c87d3, "environmental");
	public static final RegistryObject<Block> WISTERIA_WHITE = genBlock2("fake_wisteria_white", new NWISSaplingBlock(PROP_SAPLING), 0xb9b5a8, "environmental");
	// Corals
	public static final ArrayList<RegistryObject<Block>> TUBE = genCoral("tube", false, 0x304DD9);
	public static final ArrayList<RegistryObject<Block>> BRAIN = genCoral("brain", false, 0xDF7CB5);
	public static final ArrayList<RegistryObject<Block>> BUBBLE = genCoral("bubble", false, 0x8E168E);
	public static final ArrayList<RegistryObject<Block>> FIRE = genCoral("fire", false, 0xC22936);
	public static final ArrayList<RegistryObject<Block>> HORN = genCoral("horn", false, 0xE8E74A);
	// Upgrade Aquatic Corals
	public static final ArrayList<RegistryObject<Block>> ACAN = genCoral("acan", true, 0x1A9BA0);
	public static final ArrayList<RegistryObject<Block>> CHROME = genCoral("chrome", true, 0x848787);
	public static final ArrayList<RegistryObject<Block>> FINGER = genCoral("finger", true, 0xE76800);
	public static final ArrayList<RegistryObject<Block>> STAR = genCoral("star",true, 0x8AD941);
	public static final ArrayList<RegistryObject<Block>> MOSS = genCoral("moss",true, 0x5E7C18);
	public static final ArrayList<RegistryObject<Block>> PETAL = genCoral("petal",true, 0x2EAFE0);
	public static final ArrayList<RegistryObject<Block>> BRANCH = genCoral("branch",true, 0x3E3E4D);
	public static final ArrayList<RegistryObject<Block>> ROCK = genCoral("rock",true, 0xA57855);
	public static final ArrayList<RegistryObject<Block>> PILLOW = genCoral("pillow",true, 0xEDEAE8);
	public static final ArrayList<RegistryObject<Block>> SILK = genCoral("silk",true, 0x7F2EAA);
	public static final ArrayList<RegistryObject<Block>> PRISMARINE = genCoral("prismarine",true, 0x46A9B0);
	
	public static final RegistryObject<Block> PRISMARINE_SHOWER = genBlock2("fake_prismarine_shower", new CoralFanBlock(PROP_CORAL_T, ECoralType.SHOWER, null), 0x46A9B0, "upgrade_aquatic");
	public static final RegistryObject<Block> ELDER_PRISMARINE_SHOWER = genBlock2("fake_elder_prismarine_shower", new CoralFanBlock(PROP_CORAL_T, ECoralType.SHOWER, null), 0xA29281, "upgrade_aquatic");
	
	//Swamp Expansion Compat
	public static final RegistryObject<Block> CATTAIL_BIG = genBlock2("fake_cattail_big", new DoubleDoubleCropBlock(PROP_CROPS, EDoubleCropType.CATTAIL), 0x6E412A, "???!!!");
	public static final RegistryObject<Block> CATTAIL = genBlock2("fake_cattail", new SingleDoubleCropBlock(PROP_CROPS, EDoubleCropType.CATTAIL), 0x6E412A, "???!!!");
	public static final RegistryObject<Block> CATTAIL_SPROUT = genBlock2("fake_cattail_sprout", new CropSproutBlock(PROP_CROPS), 0x6E412A, "environmental");
	
	public static final RegistryObject<Block> RICE_BIG = genBlock2("fake_rice_big", new RiceDoubleSingleBlock(PROP_CROPS), 0xBFA355, "???!!!");
	public static final RegistryObject<Block> RICE = genBlock2("fake_rice", new RiceSingleSingleBlock(PROP_CROPS), 0xBFA355, "environmental");
	
	public static final RegistryObject<Block> WILLOW_SAPLING = genBlock2("fake_willow_sap", new NWISSaplingBlock(PROP_CROPS), 0x0F3217, "environmental");
	
	public static final RegistryObject<Block> PICKELREED_BLUE_BIG = genBlock2("pickelreed_blue_big", new PickelreedDoubleBlock(PROP_CROPS), 0x2F3E87, "???!!!");
	public static final RegistryObject<Block> PICKELREED_PURPLE_BIG = genBlock2("pickelreed_purple_big", new PickelreedDoubleBlock(PROP_CROPS), 0x9649A7, "???!!!");
	
	public static final RegistryObject<Block> PICKELREED_BLUE = genBlock2("pickelreed_blue", new PickelReedSingleBlock(PROP_CROPS), 0x2F3E87, "upgrade_aquatic");
	public static final RegistryObject<Block> PICKELREED_PURPLE = genBlock2("pickelreed_purple", new PickelReedSingleBlock(PROP_CROPS), 0x9649A7, "upgrade_aquatic");
	//I'm keeping this in code as a relic for the history books.
	//public static final ArrayList<ArrayList<RegistryObject<Block>>> CORALS = new ArrayList<ArrayList<RegistryObject<Block>>>();

	
	
	//Kelps
	public static final RegistryObject<Block> KELP = genBlock2("fake_kelp", new KelpBlock(PROP_CROPS), 0x458425);
	public static final RegistryObject<Block> KELP_OCHRE = genBlock2("fake_kelp_ochre", new KelpBlock(PROP_CROPS), 0xA39041, "upgrade_aquatic");
	public static final RegistryObject<Block> KELP_TONGUE = genBlock2("fake_kelp_tongue", new KelpBlock(PROP_CROPS), 0x8D2434, "upgrade_aquatic");
	public static final RegistryObject<Block> KELP_POLAR = genBlock2("fake_kelp_polar", new KelpBlock(PROP_CROPS), 0x347C59, "upgrade_aquatic");
	public static final RegistryObject<Block> KELP_THORNY = genBlock2("fake_kelp_thorny", new KelpBlock(PROP_CROPS), 0x605341, "upgrade_aquatic");
	
	//Seagrass
	public static final RegistryObject<Block> SEAGRASS = genBlock2("fake_seagrass", new TallOrientableBlock(ETallTypes.LARGE_FERN, PROP_CROPS), 0x379006);
	public static final RegistryObject<Block> SEAGRASS_SMOL = genBlock2("fake_seagrass_small", new OrientableVeggies(EOrientables.GRASS, PROP_CROPS), 0x379006);
	
	public static final RegistryObject<Block> BEACHGRASS = genBlock2("fake_beachgrass", new TallOrientableBlock(ETallTypes.TALL_GRASS, PROP_CROPS), 0x8D994C, "upgrade_aquatic");
	public static final RegistryObject<Block> BEACHGRASS_SMOL = genBlock2("fake_beachgrass_small", new OrientableVeggies(EOrientables.GRASS, PROP_CROPS), 0x8D994C, "upgrade_aquatic");

	//Orientables...
	public static final RegistryObject<Block> FAKE_GRASS = genBlock2("fake_grass_b", new OrientableVeggies(EOrientables.GRASS, PROP_CROPS), 0x395832);
	public static final RegistryObject<Block> FAKE_TALL_GRASS = genBlock2("fake_tallgrass", new TallOrientableBlock(ETallTypes.TALL_GRASS, PROP_TALL_GRASS), 0x395832);
	
	//Tall....
	public static final RegistryObject<Block> FAKE_PEONY = genBlock2("fake_peony", new TallOrientableBlock(ETallTypes.PEONY, PROP_TALL_GRASS), 0xEBC5FD);
	public static final RegistryObject<Block> FAKE_ROSEBUSH = genBlock2("fake_rose_bush", new TallOrientableBlock(ETallTypes.TALL_ROSE, PROP_TALL_GRASS), 0xBF2529);
	public static final RegistryObject<Block> FAKE_LILAC = genBlock2("fake_lilac", new TallOrientableBlock(ETallTypes.LILAC, PROP_TALL_GRASS), 0xD380D3);
	public static final RegistryObject<Block> FAKE_TALL_FERN = genBlock2("fake_large_fern", new TallOrientableBlock(ETallTypes.LARGE_FERN, PROP_TALL_GRASS), 0x395832);
	public static final RegistryObject<Block> FAKE_SUNFLOWER = genBlock2("fake_sunflower", new TallOrientableBlock(ETallTypes.SUNFLOWER, PROP_TALL_GRASS), 0xFED639);
	//It do be flowers doe :ono:
	public static final RegistryObject<Block> FAKE_FERN = genBlock2("fake_fern", new OrientableVeggies(EOrientables.FERN, PROP_TALL_GRASS), 0x395832);
	public static final RegistryObject<Block> FAKE_DEAD_BUSH = genBlock2("fake_dead_bush", new OrientableVeggies(EOrientables.DEAD_BUSH, PROP_TALL_GRASS), 0x395832);
	
	public static final RegistryObject<Block> FAKE_ALLIUM = genBlock2("fake_allium", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0xD2A6F6);
	public static final RegistryObject<Block> FAKE_AZURE_BLUET = genBlock2("fake_azure_bluet", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0xD6E8E8);
	public static final RegistryObject<Block> FAKE_BLUE_ORCHID = genBlock2("fake_blue_orchid", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0x27A9F4);
	public static final RegistryObject<Block> FAKE_CORNFLOWER = genBlock2("fake_cornflower", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0x728FF1);
	public static final RegistryObject<Block> FAKE_DANDELION = genBlock2("fake_dandelion", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0xFFEC4F);
	public static final RegistryObject<Block> FAKE_LILY_OF_THE_VALLEY = genBlock2("fake_lily_of_the_valley", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0xE7E7E7);
	public static final RegistryObject<Block> FAKE_OXEYE_DAISY = genBlock2("fake_oxeye_daisy", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0xD6E8E8);
	public static final RegistryObject<Block> FAKE_POPPY = genBlock2("fake_poppy", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0xED302C);
	public static final RegistryObject<Block> FAKE_WITHER_ROSE = genBlock2("fake_wither_rose", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0x171210);
	
	public static final RegistryObject<Block> FAKE_RED_TULIP = genBlock2("fake_red_tulip", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0x9B221A);
	public static final RegistryObject<Block> FAKE_WHITE_TULIP = genBlock2("fake_white_tulip", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0x9BBDBD);
	public static final RegistryObject<Block> FAKE_ORANGE_TULIP = genBlock2("fake_orange_tulip", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0xF19D25);
	public static final RegistryObject<Block> FAKE_PINK_TULIP = genBlock2("fake_pink_tulip", new OrientableVeggies(EOrientables.FLOWER, PROP_TALL_GRASS), 0xEBC5FD);
	
	//Buzzier Bees compat
	/*
	public static final RegistryObject<Block> FAKE_HIBISCUS_RED = genBlock2("fake_hibiscus_red", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xEC332C, "buzzierbees");
	public static final RegistryObject<Block> FAKE_HIBISCUS_PINK = genBlock2("fake_hibiscus_pink", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xFC99BC, "buzzierbees");
	public static final RegistryObject<Block> FAKE_HIBISCUS_MAGENTA = genBlock2("fake_hibiscus_magenta", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xCF63B7, "buzzierbees");
	public static final RegistryObject<Block> FAKE_HIBISCUS_PURPLE = genBlock2("fake_hibiscus_purple", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xA733D0, "buzzierbees");
	public static final RegistryObject<Block> FAKE_HIBISCUS_ORANGE = genBlock2("fake_hibiscus_orange", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xE77D25, "buzzierbees");
	public static final RegistryObject<Block> FAKE_HIBISCUS_YELLOW = genBlock2("fake_hibiscus_yellow", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xFCE565, "buzzierbees");
	
	public static final RegistryObject<Block> FAKE_DIANTHUS = genBlock2("fake_dianthus", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0x1C7132, "buzzierbees");
	public static final RegistryObject<Block> FAKE_WHITE_CLOVER = genBlock2("fake_white_clover", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xF1D9EB, "buzzierbees");
	public static final RegistryObject<Block> FAKE_PINK_CLOVER = genBlock2("fake_pink_clover", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xF4A3D0, "buzzierbees");
	public static final RegistryObject<Block> FAKE_COLUMBINE = genBlock2("fake_columbine", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xAC9ADF, "buzzierbees");
	public static final RegistryObject<Block> FAKE_CARTWHEEL = genBlock2("fake_cartwheel", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0xD8939E, "buzzierbees");
	public static final RegistryObject<Block> FAKE_BLUEBELL = genBlock2("fake_bluebell", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0x456CC7, "buzzierbees");
	public static final RegistryObject<Block> FAKE_VIOLET = genBlock2("fake_violet", new OrientableVeggies(EOrientables.SAPLING, PROP_TALL_GRASS), 0x81559E, "buzzierbees");
	public static final RegistryObject<Block> FAKE_BIRD_OF_PARADISE = genBlock2("fake_bird_of_paradise", new TallOrientableBlock(ETallTypes.TALL_GRASS, PROP_TALL_GRASS), 0x5C61C4, "buzzierbees");
	*/
	//1.16
	public static final RegistryObject<Block> STATIC_NYLIUM_CRIMSON = genBlock("fake_nylium_crimson", new NWISOrientableBlock(PROP_NYLIUM), 0);
	public static final RegistryObject<Block> STATIC_NYLIUM_WARPED = genBlock("fake_nylium_warped", new NWISOrientableBlock(PROP_NYLIUM), 0);
	public static final RegistryObject<Block> STATIC_NYLIUM_CRIMSON_A = genBlock("fake_nylium_crimson_a", new NWISOrientableBlock(PROP_NYLIUM), 0);
	public static final RegistryObject<Block> STATIC_NYLIUM_WARPED_A = genBlock("fake_nylium_warped_a", new NWISOrientableBlock(PROP_NYLIUM), 0);
	
	public static final RegistryObject<Block> FAKE_WEEPING_VINE = genBlock2("fake_weeping_vine", new NetherRootStuff(EOrientables.TALL_SEAGRASS, PROP_CROPS), 0x920A0A);
	public static final RegistryObject<Block> FAKE_TWISTING_VINE = genBlock2("fake_twisting_vine", new NetherRootStuff(EOrientables.TALL_SEAGRASS, PROP_CROPS), 0x197977);

	public static final RegistryObject<Block> FAKE_CRIMSON_ROOT = genBlock2("fake_crimson_root", new OrientableVeggies(EOrientables.GRASS, PROP_CROPS), 0x920A0A);
	public static final RegistryObject<Block> FAKE_WARPED_ROOT = genBlock2("fake_warped_root", new OrientableVeggies(EOrientables.GRASS, PROP_CROPS), 0x197977);
	
	public static final RegistryObject<Block> FAKE_WARPED_FUNGUS = genBlock2("fake_warped_fungus", new OrientableVeggies(EOrientables.MUSHROOM, PROP_FUNGUS), 0x197977);
	public static final RegistryObject<Block> FAKE_CRIMSON_FUNGUS = genBlock2("fake_crimson_fungus", new OrientableVeggies(EOrientables.MUSHROOM, PROP_FUNGUS), 0x920A0A);
	
	public static final RegistryObject<Block> FAKE_NETHER_SPROUT = genBlock2("fake_nether_sprout", new OrientableVeggies(EOrientables.GRASS, PROP_CROPS), 0x197977);

	public static final RegistryObject<Block> FAKE_CHAIN = genBlock2("fake_chain", new OrientableVeggies(EOrientables.GRASS, PROP_SEMISOLIDS), 0);
	public static final RegistryObject<Block> FAKE_LANTERN = genBlock2("fake_lantern", new NetherRootStuff(EOrientables.GRASS, PROP_SEMISOLIDS), 0);
	public static final RegistryObject<Block> FAKE_SOUL_LANTERN = genBlock2("fake_soul_lantern", new NetherRootStuff(EOrientables.GRASS, PROP_SEMISOLIDS), 0);

	public static final RegistryObject<Block> FAKE_ANCIENT_DEBRIS = genBlock2("fake_ancient_debris", new NWISOrientableBlock(PROP_ANCIENTDEBRIS), 0);

	public static final RegistryObject<Block> FAKE_TORCH = genBlock2("fake_torch", new NWISOrientableBlock(PROP_TORCH), 0);
	public static final RegistryObject<Block> FAKE_SOUL_TORCH = genBlock2("fake_soul_torch", new NWISOrientableBlock(PROP_TORCH), 0);
}