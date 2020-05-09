package com.gkoliver.nwis.core.register;

import java.util.ArrayList;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.common.block.crop.ECropTypes;
import com.gkoliver.nwis.common.block.crop.FakeAttachedBlock;
import com.gkoliver.nwis.common.block.crop.FakeBeetrootBlock;
import com.gkoliver.nwis.common.block.crop.FakeGrowableBlock;
import com.gkoliver.nwis.common.block.other.ImposterStationBlock;
import com.gkoliver.nwis.common.block.other.NWISBlock;
import com.gkoliver.nwis.common.block.other.NWISNorthableBlock;
import com.gkoliver.nwis.common.block.other.NWISWaterLogBlock;
import com.gkoliver.nwis.common.block.other.RestrainedDillutedPortalBlock;
import com.gkoliver.nwis.common.block.other.VoidBlock;
import com.gkoliver.nwis.common.block.vegitation.ChorusFruitBlock;
import com.gkoliver.nwis.common.block.vegitation.CoralWallFanBlock;
import com.gkoliver.nwis.common.block.vegitation.ECoralType;
import com.gkoliver.nwis.common.block.vegitation.FakeVineBlock;
import com.gkoliver.nwis.common.block.vegitation.NewChorusPlantBlock;
import com.gkoliver.nwis.common.block.vegitation.SemiInvisibleBlock;
import com.gkoliver.nwis.common.block.vegitation.SemiInvisibleNorthableBlock;
import com.gkoliver.nwis.common.block.vegitation.SmallCoralBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.CropSproutBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.DoubleDoubleCropBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.EDoubleCropType;
import com.gkoliver.nwis.common.block.vegitation.swex.SingleDoubleCropBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.rice.RiceDoubleSingleBlock;
import com.gkoliver.nwis.common.block.vegitation.swex.rice.RiceSingleSingleBlock;
import com.gkoliver.nwis.common.gui.ImposterContainer;
import com.gkoliver.nwis.core.event.ClientEvents;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
		
		BlockItem item = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
		ItemRegistry.ITEMS.register(id, () -> item);
		return BlockRegistry.BLOCKS.register(id, () -> block);
	}
	public static RegistryObject<Block> genBlock(String id, Block block, int color) {
		
		BlockItem item = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
		ItemRegistry.ITEMS.register(id, () -> item);
		if (color!=0) {
			ClientEvents.COLOR_MAPS.put(item, color);
		}
		return BlockRegistry.BLOCKS.register(id, () -> block);
	}
	public static RegistryObject<Block> genBlock2(String id, Block block) {
		BlockItem item = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
		ItemRegistry.ITEMS.register(id, () -> item);
		CUTOUTS.add(block);
		return BlockRegistry.BLOCKS.register(id, () -> block);
	}
	public static RegistryObject<Block> genBlock2(String id, Block block, int color) {
		BlockItem item = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
		ItemRegistry.ITEMS.register(id, () -> item);
		CUTOUTS.add(block);
		if (color!=0) {
			ClientEvents.COLOR_MAPS.put(item, color);
		}
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
	public static ArrayList<RegistryObject<Block>> genCoral(String name, boolean ua, int color) {
		ArrayList<RegistryObject<Block>> tbr = new ArrayList<RegistryObject<Block>>();
		NWISBlock alive_block = new NWISBlock(PROP_CORAL);
		NWISBlock dead_block = new NWISBlock(PROP_CORAL);
		SmallCoralBlock alive = new SmallCoralBlock(PROP_CORAL_T, ECoralType.SMALL, null);
		SmallCoralBlock dead = new SmallCoralBlock(PROP_CORAL_T, ECoralType.SMALL, null);
		CoralWallFanBlock alive_wall_fan = new CoralWallFanBlock(PROP_CORAL_T);
		CoralWallFanBlock dead_wall_fan = new CoralWallFanBlock(PROP_CORAL_T);
		SmallCoralBlock alive_fan = new SmallCoralBlock(PROP_CORAL_T, ECoralType.FAN, alive_wall_fan);
		SmallCoralBlock dead_fan = new SmallCoralBlock(PROP_CORAL_T, ECoralType.FAN, dead_wall_fan);
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
			if (ua) {
				if (NotWhatItSeems.ua) {
					bi = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
				} else {
					bi = new BlockItem(block, new Item.Properties());
				}
			} else {
				bi = new BlockItem(block, new Item.Properties().group(ItemGroup.SEARCH));
			}
			if (stringy.get(i).contains("dead")) {
				if (stringy.get(i).contains("prismarine")) {
					ClientEvents.COLOR_MAPS.put(bi, 0xA29281);
				} else {
					ClientEvents.COLOR_MAPS.put(bi, 0x808080);
				}
			} else {
				ClientEvents.COLOR_MAPS.put(bi, color);
			}
			
			
			ItemRegistry.ITEMS.register(stringy.get(i), () -> bi);
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
	public static final Block.Properties PROP_VOID = Block.Properties.create(Material.PORTAL).notSolid();
	public static final Block.Properties PROP_SAPLING = Block.Properties.create(Material.PLANTS).doesNotBlockMovement()
			.notSolid().sound(SoundType.PLANT);
	public static final Block.Properties PROP_SOIL = Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT);
	public static final Block.Properties PROP_MUSH = Block.Properties.create(Material.ORGANIC).sound(SoundType.WOOD);
	public static final Block.Properties PROP_GLOWMUSH = Block.Properties.create(Material.ORGANIC).sound(SoundType.WOOD).notSolid();
	public static final Block.Properties PROP_STATION = Block.Properties.create(Material.ROCK).sound(SoundType.METAL);
	public static final Block.Properties PROP_CORAL = Block.Properties.create(Material.CORAL).sound(SoundType.CORAL);
	public static final Block.Properties PROP_CORAL_T = Block.Properties.create(Material.CORAL).sound(SoundType.CORAL)
			.doesNotBlockMovement().notSolid();
	public static final Block.Properties PROP_CHORUS_FRUIT = Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).notSolid();
	private static final Properties PROP_VOID_SEMI = Block.Properties.create(Material.PORTAL).notSolid().doesNotBlockMovement();
	
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

	public static final RegistryObject<Block> FAKE_PUMPKIN_STEM_ATTACHED = genBlock2("fake_pumpkin_stem_attached",
			new FakeAttachedBlock(PROP_CROPS, ECropTypes.PUMPKIN_STEM), 0);
	public static final RegistryObject<Block> FAKE_MELON_STEM_ATTACHED = genBlock2("fake_melon_stem_attached",
			new FakeAttachedBlock(PROP_CROPS, ECropTypes.MELON_STEM), 0);
	
	public static final RegistryObject<Block> VOID_BLOCK = genBlock("void_block", new VoidBlock(PROP_VOID), 0);
	public static final RegistryObject<Block> DILLUTED_VOID_BLOCK = genBlock("dilluted_void_block",
			new SemiInvisibleBlock(PROP_VOID), 0);
	public static final RegistryObject<Block> VOID_BLOCK_SEMISOLID = genBlock("void_block_semi", new VoidBlock(PROP_VOID_SEMI), 0);
	
	public static final RegistryObject<Block> DILLUTED_VOID_BLOCK_SEMISOLID = genBlock("dilluted_void_block_semi",
			new SemiInvisibleBlock(PROP_VOID_SEMI), 0);
	public static final RegistryObject<Block> RESTRAINED_DILLUTED_VOID_BLOCK = genBlock(
			"restrained_dilluted_void_block", new RestrainedDillutedPortalBlock(PROP_VOID), 0);

	public static final RegistryObject<Block> FAKE_OAK_SAPLING = genBlock2("fake_oak_sapling",
			new NWISWaterLogBlock(PROP_SAPLING), 0x408F2F);
	public static final RegistryObject<Block> FAKE_BIRCH_SAPLING = genBlock2("fake_birch_sapling",
			new NWISWaterLogBlock(PROP_SAPLING), 0x6C9E38);
	public static final RegistryObject<Block> FAKE_SPRUCE_SAPLING = genBlock2("fake_spruce_sapling",
			new NWISWaterLogBlock(PROP_SAPLING), 0x2E492E);
	public static final RegistryObject<Block> FAKE_JUNGLE_SAPLING = genBlock2("fake_jungle_sapling",
			new NWISWaterLogBlock(PROP_SAPLING), 0x2B4A0C);
	public static final RegistryObject<Block> FAKE_ACACIA_SAPLING = genBlock2("fake_acacia_sapling",
			new NWISWaterLogBlock(PROP_SAPLING), 0x7E9821);
	public static final RegistryObject<Block> FAKE_DARK_OAK_SAPLING = genBlock2("fake_dark_oak_sapling",
			new NWISWaterLogBlock(PROP_SAPLING), 0x105210);

	public static final RegistryObject<Block> FAKE_VINE = genBlock2("fake_vine", new FakeVineBlock(PROP_CROPS), 0x255E0D);

	public static final RegistryObject<Block> FAKE_MUSHOOM_1 = genBlock2("fake_mushroom_red",
			new NWISWaterLogBlock(PROP_SAPLING), 0xFE2A2A);
	public static final RegistryObject<Block> FAKE_MUSHOOM_2 = genBlock2("fake_mushroom_brown",
			new NWISWaterLogBlock(PROP_SAPLING), 0x916D55);

	// Grass Blocks
	public static final RegistryObject<Block> STATIC_GRASS = genBlock("fake_grass", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_MYCELIUM = genBlock("fake_mycelium", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_PODZOL = genBlock("fake_podzol", new NWISBlock(PROP_SOIL), 0);

	public static final RegistryObject<Block> STATIC_GRASS_A = genBlock("fake_grass_a", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_MYCELIUM_A = genBlock("fake_mycelium_a", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_PODZOL_A = genBlock("fake_podzol_a", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> IMPOSTER_STATION = genBlock("imposter_station",
			new ImposterStationBlock(PROP_SOIL), 0);
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

	// Endergetic
	public static final RegistryObject<Block> STATIC_POSIMOSS = genBlock("fake_posimoss", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_POSIMOSS_A = genBlock("fake_posimoss_a", new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_POSIMOSS_EUMUS = genBlock("fake_posimoss_eumus",
			new NWISBlock(PROP_SOIL), 0);
	public static final RegistryObject<Block> STATIC_POISE_CLUSTER = genBlock("fake_poise_cluster",
			new SemiInvisibleBlock(PROP_SOIL), 0);

	// Quark: Glowing Caves
	public static final RegistryObject<Block> FAKE_GLOWCELIUM = genBlock("fake_glowcelium", new NWISBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_GLOWCELIUM_A = genBlock("fake_glowcelium_a",
			new NWISBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_BIG_GLOWSHROOM = genBlock("fake_big_glowshroom",
			new SemiInvisibleNorthableBlock(PROP_GLOWMUSH));
	public static final RegistryObject<Block> FAKE_BIG_GLOWSHROOM_STEM = genBlock("fake_big_glowshroom_stem",
			new SemiInvisibleNorthableBlock(PROP_GLOWMUSH));
	public static final RegistryObject<Block> FAKE_GLOWSHROOM = genBlock2("fake_glowshroom", new NWISBlock(PROP_SAPLING), 0x6BD8F8);
	public static final RegistryObject<Block> FAKE_CAVE_ROOTS = genBlock("fake_cave_roots", new FakeVineBlock(PROP_MUSH), 0xCBA365);
	// Sapling
	public static final RegistryObject<Block> FROSTY_SAPLING = genBlock2("fake_qsap_frosty", new NWISBlock(PROP_SAPLING), 0x3C95B7);
	public static final RegistryObject<Block> SERENE_SAPLING = genBlock2("fake_qsap_serene", new NWISBlock(PROP_SAPLING), 0x9D7EAA);
	public static final RegistryObject<Block> WARM_SAPLING = genBlock2("fake_qsap_warm", new NWISBlock(PROP_SAPLING), 0xE18D2B);
	public static final RegistryObject<Block> SUNNY_SAPLING = genBlock2("fake_qsap_sunny", new NWISBlock(PROP_SAPLING), 0xC5BC53);
	public static final RegistryObject<Block> SWEET_SAPLING = genBlock2("fake_qsap_sweet", new NWISBlock(PROP_SAPLING), 0xE5B7D3);
	// Autuminity
	public static final RegistryObject<Block> MAPLE_SAPLING = genBlock2("fake_maple_sapling", new NWISBlock(PROP_SAPLING), 0x31621B);
	public static final RegistryObject<Block> MAPLE_SAPLING_RED = genBlock2("fake_maple_sapling_red",
			new NWISBlock(PROP_SAPLING), 0xBA420E);
	public static final RegistryObject<Block> MAPLE_SAPLING_YELLOW = genBlock2("fake_maple_sapling_yellow",
			new NWISBlock(PROP_SAPLING), 0x977200);
	public static final RegistryObject<Block> MAPLE_SAPLING_ORANGE = genBlock2("fake_maple_sapling_orange",
			new NWISBlock(PROP_SAPLING), 0x924D17);
	// Atmospheric
	public static final RegistryObject<Block> PASSION_VINE = genBlock2("fake_passion_vine", new NWISBlock(PROP_MUSH), 0x7A0145);
	public static final RegistryObject<Block> ROSEWOOD_SAPLING = genBlock2("fake_rosewood_sapling", new NWISBlock(PROP_MUSH), 0x437224);
	public static final RegistryObject<Block> YUCCA_SAPLING = genBlock2("fake_yucca_sapling", new NWISBlock(PROP_MUSH), 0x85A143);
	public static final RegistryObject<Block> ASPEN_SAPLING = genBlock2("fake_aspen_sapling", new NWISBlock(PROP_MUSH), 0xFFD556);
	public static final RegistryObject<Block> KOUSA_SAPLING = genBlock2("fake_kousa_sapling", new NWISBlock(PROP_MUSH), 0xBBDDD1);
	// Bloomful
	public static final RegistryObject<Block> WISTERIA_PINK = genBlock2("fake_wisteria_pink", new NWISBlock(PROP_MUSH), 0xf29bbb);
	public static final RegistryObject<Block> WISTERIA_BLUE = genBlock2("fake_wisteria_blue", new NWISBlock(PROP_MUSH), 0x6c95c9);
	public static final RegistryObject<Block> WISTERIA_PURPLE = genBlock2("fake_wisteria_purple", new NWISBlock(PROP_MUSH), 0x9c87d3);
	public static final RegistryObject<Block> WISTERIA_WHITE = genBlock2("fake_wisteria_white", new NWISBlock(PROP_MUSH), 0xb9b5a8);
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
	
	public static final RegistryObject<Block> PRISMARINE_SHOWER = genBlock2("fake_prismarine_shower", new SmallCoralBlock(PROP_CORAL_T, ECoralType.SHOWER, null), 0x46A9B0);
	public static final RegistryObject<Block> ELDER_PRISMARINE_SHOWER = genBlock2("fake_elder_prismarine_shower", new SmallCoralBlock(PROP_CORAL_T, ECoralType.SHOWER, null), 0xA29281);
	
	//Swamp Expansion Compat
	public static final RegistryObject<Block> CATTAIL_BIG = genBlock2("fake_cattail_big", new DoubleDoubleCropBlock(PROP_CROPS, EDoubleCropType.CATTAIL));
	public static final RegistryObject<Block> CATTAIL = genBlock2("fake_cattail", new SingleDoubleCropBlock(PROP_CROPS, EDoubleCropType.CATTAIL));
	public static final RegistryObject<Block> CATTAIL_SPROUT = genBlock2("fake_cattail_sprout", new CropSproutBlock(PROP_CROPS));
	public static final RegistryObject<Block> RICE_BIG = genBlock2("fake_rice_big", new RiceDoubleSingleBlock(PROP_CROPS));
	public static final RegistryObject<Block> RICE = genBlock2("fake_rice", new RiceSingleSingleBlock(PROP_CROPS));
	public static final RegistryObject<Block> WILLOW_SAPLING = genBlock2("fake_willow_sap", new NWISBlock(PROP_CROPS));
	
	
	//I'm keeping this in code as a relic for the history books.
	//public static final ArrayList<ArrayList<RegistryObject<Block>>> CORALS = new ArrayList<ArrayList<RegistryObject<Block>>>();

}
