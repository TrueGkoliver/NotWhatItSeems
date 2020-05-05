package com.gkoliver.nwis.core.register;

import java.util.ArrayList;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.common.block.crop.ECropTypes;
import com.gkoliver.nwis.common.block.crop.FakeAttachedBlock;
import com.gkoliver.nwis.common.block.crop.FakeGrowableBlock;
import com.gkoliver.nwis.common.block.other.ImposterStationBlock;
import com.gkoliver.nwis.common.block.other.NWISBlock;
import com.gkoliver.nwis.common.block.other.NWISNorthableBlock;
import com.gkoliver.nwis.common.block.other.RestrainedDillutedPortalBlock;
import com.gkoliver.nwis.common.block.other.VoidBlock;
import com.gkoliver.nwis.common.block.vegitation.CoralWallFanBlock;
import com.gkoliver.nwis.common.block.vegitation.ECoralType;
import com.gkoliver.nwis.common.block.vegitation.FakeVineBlock;
import com.gkoliver.nwis.common.block.vegitation.SmallCoralBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS,
			NotWhatItSeems.MODID);
	/**
	 * Generates a block with no creative tab.
	 * 
	 * @return
	 */
	public static ArrayList<Block> CUTOUTS = new ArrayList<Block>();

	public static RegistryObject<Block> genBlock(String id, Block block) {
		BlockItem item = new BlockItem(block, new Item.Properties());
		ItemRegistry.ITEMS.register(id, () -> item);
		return BlockRegistry.BLOCKS.register(id, () -> block);
	}

	public static RegistryObject<Block> genBlock2(String id, Block block) {
		BlockItem item = new BlockItem(block, new Item.Properties());
		ItemRegistry.ITEMS.register(id, () -> item);
		CUTOUTS.add(block);
		return BlockRegistry.BLOCKS.register(id, () -> block);
	}

	// 0: Alive Block
	// 1: Dead Block
	// 2: Alive Coral
	// 3: Dead Coral
	// 4: Alive Coral Fan
	// 5: Dead Coral Fan
	// 6: Alive Coral Fan (Wall)
	// 7: Dead Coral Fan (Wall)
	public static ArrayList<RegistryObject<Block>> genCoral(String name) {
		ArrayList<RegistryObject<Block>> tbr = new ArrayList<RegistryObject<Block>>();
		NWISBlock alive_block = new NWISBlock(PROP_CORAL);
		NWISBlock dead_block = new NWISBlock(PROP_CORAL);
		SmallCoralBlock alive = new SmallCoralBlock(PROP_CORAL_T, ECoralType.SMALL);
		SmallCoralBlock dead = new SmallCoralBlock(PROP_CORAL_T, ECoralType.SMALL);
		SmallCoralBlock alive_fan = new SmallCoralBlock(PROP_CORAL_T, ECoralType.FAN);
		SmallCoralBlock dead_fan = new SmallCoralBlock(PROP_CORAL_T, ECoralType.FAN);
		CoralWallFanBlock alive_wall_fan = new CoralWallFanBlock(PROP_CORAL_T);
		CoralWallFanBlock dead_wall_fan = new CoralWallFanBlock(PROP_CORAL_T);
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
			BlockItem bi = new BlockItem(block, new Item.Properties());
			ItemRegistry.ITEMS.register(stringy.get(i), () -> bi);
		}
		CUTOUTS.add(alive);
		CUTOUTS.add(dead);
		CUTOUTS.add(alive_fan);
		CUTOUTS.add(dead_fan);
		return tbr;

	}

	public static final Block.Properties PROP_CROPS = Block.Properties.create(Material.PLANTS).doesNotBlockMovement()
			.notSolid().sound(SoundType.PLANT);
	public static final Block.Properties PROP_VOID = Block.Properties.create(Material.PORTAL);
	public static final Block.Properties PROP_SAPLING = Block.Properties.create(Material.PLANTS).doesNotBlockMovement()
			.notSolid().sound(SoundType.PLANT);
	public static final Block.Properties PROP_SOIL = Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT);
	public static final Block.Properties PROP_MUSH = Block.Properties.create(Material.ORGANIC).sound(SoundType.WOOD);
	public static final Block.Properties PROP_STATION = Block.Properties.create(Material.ROCK).sound(SoundType.METAL);
	public static final Block.Properties PROP_CORAL = Block.Properties.create(Material.CORAL).sound(SoundType.CORAL);
	public static final Block.Properties PROP_CORAL_T = Block.Properties.create(Material.CORAL).sound(SoundType.CORAL)
			.doesNotBlockMovement().notSolid();
	public static final RegistryObject<Block> FAKE_CARROTS = genBlock2("fake_carrot",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.CARROT));
	public static final RegistryObject<Block> FAKE_POTATO = genBlock2("fake_potato",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.POTATO));
	public static final RegistryObject<Block> FAKE_WHEAT = genBlock2("fake_wheat",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.WHEAT));
	public static final RegistryObject<Block> FAKE_BEETROOT = genBlock2("fake_beetroot",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.BEETROOT));
	public static final RegistryObject<Block> FAKE_MELON_STEM = genBlock2("fake_melon_stem",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.MELON_STEM));
	public static final RegistryObject<Block> FAKE_PUMPKIN_STEM = genBlock2("fake_pumpkin_stem",
			new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.PUMPKIN_STEM));

	public static final RegistryObject<Block> FAKE_PUMPKIN_STEM_ATTACHED = genBlock2("fake_pumpkin_stem_attached",
			new FakeAttachedBlock(PROP_CROPS, ECropTypes.PUMPKIN_STEM));
	public static final RegistryObject<Block> FAKE_MELON_STEM_ATTACHED = genBlock2("fake_melon_stem_attached",
			new FakeAttachedBlock(PROP_CROPS, ECropTypes.MELON_STEM));
	public static final RegistryObject<Block> VOID_BLOCK = genBlock("void_block", new VoidBlock(PROP_VOID));
	public static final RegistryObject<Block> DILLUTED_VOID_BLOCK = genBlock("dilluted_void_block",
			new NWISBlock(PROP_VOID));
	public static final RegistryObject<Block> RESTRAINED_DILLUTED_VOID_BLOCK = genBlock(
			"restrained_dilluted_void_block", new RestrainedDillutedPortalBlock(PROP_VOID));

	public static final RegistryObject<Block> FAKE_OAK_SAPLING = genBlock2("fake_oak_sapling",
			new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_BIRCH_SAPLING = genBlock2("fake_birch_sapling",
			new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_SPRUCE_SAPLING = genBlock2("fake_spruce_sapling",
			new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_JUNGLE_SAPLING = genBlock2("fake_jungle_sapling",
			new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_ACACIA_SAPLING = genBlock2("fake_acacia_sapling",
			new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_DARK_OAK_SAPLING = genBlock2("fake_dark_oak_sapling",
			new NWISBlock(PROP_SAPLING));

	public static final RegistryObject<Block> FAKE_VINE = genBlock2("fake_vine", new FakeVineBlock(PROP_CROPS));

	public static final RegistryObject<Block> FAKE_MUSHOOM_1 = genBlock2("fake_mushroom_red",
			new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_MUSHOOM_2 = genBlock2("fake_mushroom_brown",
			new NWISBlock(PROP_SAPLING));

	// Grass Blocks
	public static final RegistryObject<Block> STATIC_GRASS = genBlock("fake_grass", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_MYCELIUM = genBlock("fake_mycelium", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_PODZOL = genBlock("fake_podzol", new NWISBlock(PROP_SOIL));

	public static final RegistryObject<Block> STATIC_GRASS_A = genBlock("fake_grass_a", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_MYCELIUM_A = genBlock("fake_mycelium_a", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_PODZOL_A = genBlock("fake_podzol_a", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> IMPOSTER_STATION = genBlock("imposter_station",
			new ImposterStationBlock(PROP_SOIL));
	public static final RegistryObject<Block> SPECIAL_MUSHROOM = genBlock("fake_brown_mushroom_block",
			new NWISNorthableBlock(PROP_MUSH));
	public static final RegistryObject<Block> SPECIAL_MUSHROOM_R = genBlock("fake_red_mushroom_block",
			new NWISNorthableBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_MUSHROOM_STEM = genBlock("fake_mushroom_stem",
			new NWISNorthableBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_CHORUS = genBlock("fake_chorus_fruit_block",
			new NWISNorthableBlock(PROP_MUSH));

	// Endergetic
	public static final RegistryObject<Block> STATIC_POSIMOSS = genBlock("fake_posimoss", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_POSIMOSS_A = genBlock("fake_posimoss_a", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_POSIMOSS_EUMUS = genBlock("fake_posimoss_eumus",
			new NWISBlock(PROP_SOIL));

	// Quark: Glowing Caves
	public static final RegistryObject<Block> FAKE_GLOWCELIUM = genBlock("fake_glowcelium", new NWISBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_GLOWCELIUM_A = genBlock("fake_glowcelium_a",
			new NWISBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_BIG_GLOWSHROOM = genBlock("fake_big_glowshroom",
			new NWISNorthableBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_BIG_GLOWSHROOM_STEM = genBlock("fake_big_glowshroom_stem",
			new NWISNorthableBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_GLOWSHROOM = genBlock2("fake_glowshroom", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_CAVE_ROOTS = genBlock("fake_cave_roots", new FakeVineBlock(PROP_MUSH));
	// Sapling
	public static final RegistryObject<Block> FROSTY_SAPLING = genBlock2("fake_qsap_frosty", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> SERENE_SAPLING = genBlock2("fake_qsap_serene", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> WARM_SAPLING = genBlock2("fake_qsap_warm", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> SUNNY_SAPLING = genBlock2("fake_qsap_sunny", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> SWEET_SAPLING = genBlock2("fake_qsap_sweet", new NWISBlock(PROP_SAPLING));
	// Autuminity
	public static final RegistryObject<Block> MAPLE_SAPLING = genBlock2("fake_maple_sapling", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> MAPLE_SAPLING_RED = genBlock2("fake_maple_sapling_red",
			new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> MAPLE_SAPLING_YELLOW = genBlock2("fake_maple_sapling_yellow",
			new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> MAPLE_SAPLING_ORANGE = genBlock2("fake_maple_sapling_orange",
			new NWISBlock(PROP_SAPLING));
	// Atmospheric
	public static final RegistryObject<Block> PASSION_VINE = genBlock2("fake_passion_vine", new NWISBlock(PROP_MUSH));
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = genBlock2("fake_rosewood_sapling", new NWISBlock(PROP_MUSH));
	// Bloomful
	public static final RegistryObject<Block> WISTERIA_PINK = genBlock2("fake_wisteria_pink", new NWISBlock(PROP_MUSH));
	public static final RegistryObject<Block> WISTERIA_BLUE = genBlock2("fake_wisteria_blue", new NWISBlock(PROP_MUSH));
	public static final RegistryObject<Block> WISTERIA_PURPLE = genBlock2("fake_wisteria_purple", new NWISBlock(PROP_MUSH));
	public static final RegistryObject<Block> WISTERIA_WHITE = genBlock2("fake_wisteria_white", new NWISBlock(PROP_MUSH));
	// Corals
	public static final ArrayList<RegistryObject<Block>> TUBE = genCoral("tube");
	public static final ArrayList<RegistryObject<Block>> BRAIN = genCoral("brain");
	public static final ArrayList<RegistryObject<Block>> BUBBLE = genCoral("bubble");
	public static final ArrayList<RegistryObject<Block>> FIRE = genCoral("fire");
	public static final ArrayList<RegistryObject<Block>> HORN = genCoral("horn");
	// Upgrade Aquatic Corals
	public static final ArrayList<RegistryObject<Block>> ACAN = genCoral("acan");
	public static final ArrayList<RegistryObject<Block>> FINGER = genCoral("finger");
	public static final ArrayList<RegistryObject<Block>> STAR = genCoral("star");
	public static final ArrayList<RegistryObject<Block>> MOSS = genCoral("moss");
	public static final ArrayList<RegistryObject<Block>> PETAL = genCoral("petal");
	public static final ArrayList<RegistryObject<Block>> BRANCH = genCoral("branch");
	public static final ArrayList<RegistryObject<Block>> ROCK = genCoral("rock");
	public static final ArrayList<RegistryObject<Block>> PILLOW = genCoral("pillow");
	public static final ArrayList<RegistryObject<Block>> SILK = genCoral("silk");
	public static final ArrayList<RegistryObject<Block>> PRISMARINE = genCoral("prismarine");

}
