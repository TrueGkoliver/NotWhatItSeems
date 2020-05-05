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
import com.gkoliver.nwis.common.block.vegitation.FakeVineBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, NotWhatItSeems.MODID);
	/**
	 * Generates a block with no creative tab.
	 * @return
	 */
	public static ArrayList<Block> CUTOUTS = new ArrayList<Block>();
	public static RegistryObject<Block> genBlock(String id, Block block) {
		BlockItem item = new BlockItem(block, new Item.Properties());
		ItemRegistry.ITEMS.register(id, ()->item);
		return BlockRegistry.BLOCKS.register(id, ()->block);
	}
	public static RegistryObject<Block> genBlock2(String id, Block block) {
		BlockItem item = new BlockItem(block, new Item.Properties());
		ItemRegistry.ITEMS.register(id, ()->item);
		CUTOUTS.add(block);
		return BlockRegistry.BLOCKS.register(id, ()->block);
	}
	public static final Block.Properties PROP_CROPS = Block.Properties.create(Material.PLANTS).doesNotBlockMovement().notSolid().sound(SoundType.PLANT);
	public static final Block.Properties PROP_VOID = Block.Properties.create(Material.PORTAL);
	public static final Block.Properties PROP_SAPLING = Block.Properties.create(Material.PLANTS).doesNotBlockMovement().notSolid().sound(SoundType.PLANT);
	public static final Block.Properties PROP_SOIL = Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT);
	public static final Block.Properties PROP_MUSH = Block.Properties.create(Material.ORGANIC).sound(SoundType.WOOD);
	public static final Block.Properties PROP_STATION = Block.Properties.create(Material.ROCK).sound(SoundType.METAL);
	
	
	public static final RegistryObject<Block> FAKE_CARROTS = genBlock2("fake_carrot", new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.CARROT));
	public static final RegistryObject<Block> FAKE_POTATO = genBlock2("fake_potato", new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.POTATO));
	public static final RegistryObject<Block> FAKE_WHEAT = genBlock2("fake_wheat", new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.WHEAT));
	public static final RegistryObject<Block> FAKE_BEETROOT = genBlock2("fake_beetroot", new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.BEETROOT));
	public static final RegistryObject<Block> FAKE_MELON_STEM = genBlock2("fake_melon_stem", new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.MELON_STEM));
	public static final RegistryObject<Block> FAKE_PUMPKIN_STEM = genBlock2("fake_pumpkin_stem", new FakeGrowableBlock(PROP_CROPS, 0, ECropTypes.PUMPKIN_STEM));
	
	
	public static final RegistryObject<Block> FAKE_PUMPKIN_STEM_ATTACHED = genBlock2("fake_pumpkin_stem_attached", new FakeAttachedBlock(PROP_CROPS, ECropTypes.PUMPKIN_STEM));
	public static final RegistryObject<Block> FAKE_MELON_STEM_ATTACHED = genBlock2("fake_melon_stem_attached", new FakeAttachedBlock(PROP_CROPS, ECropTypes.MELON_STEM));
	public static final RegistryObject<Block> VOID_BLOCK = genBlock("void_block", new VoidBlock(PROP_VOID));
	public static final RegistryObject<Block> DILLUTED_VOID_BLOCK = genBlock("dilluted_void_block", new NWISBlock(PROP_VOID));
	public static final RegistryObject<Block> RESTRAINED_DILLUTED_VOID_BLOCK = genBlock("restrained_dilluted_void_block", new RestrainedDillutedPortalBlock(PROP_VOID));

	public static final RegistryObject<Block> FAKE_OAK_SAPLING = genBlock2("fake_oak_sapling", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_BIRCH_SAPLING = genBlock2("fake_birch_sapling", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_SPRUCE_SAPLING = genBlock2("fake_spruce_sapling", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_JUNGLE_SAPLING = genBlock2("fake_jungle_sapling", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_ACACIA_SAPLING = genBlock2("fake_acacia_sapling", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_DARK_OAK_SAPLING = genBlock2("fake_dark_oak_sapling", new NWISBlock(PROP_SAPLING));
	
	public static final RegistryObject<Block> FAKE_VINE = genBlock2("fake_vine", new FakeVineBlock(PROP_CROPS));
	
	public static final RegistryObject<Block> FAKE_MUSHOOM_1 = genBlock2("fake_mushroom_red", new NWISBlock(PROP_SAPLING));
	public static final RegistryObject<Block> FAKE_MUSHOOM_2 = genBlock2("fake_mushroom_brown", new NWISBlock(PROP_SAPLING));
	
	//Grass Blocks
	public static final RegistryObject<Block> STATIC_GRASS = genBlock("fake_grass", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_MYCELIUM = genBlock("fake_mycelium", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_PODZOL = genBlock("fake_podzol", new NWISBlock(PROP_SOIL));
	
	public static final RegistryObject<Block> STATIC_GRASS_A = genBlock("fake_grass_a", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_MYCELIUM_A = genBlock("fake_mycelium_a", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> STATIC_PODZOL_A = genBlock("fake_podzol_a", new NWISBlock(PROP_SOIL));
	public static final RegistryObject<Block> IMPOSTER_STATION = genBlock("imposter_station", new ImposterStationBlock(PROP_SOIL));
	public static final RegistryObject<Block> SPECIAL_MUSHROOM = genBlock("fake_brown_mushroom_block", new NWISNorthableBlock(PROP_MUSH));
	public static final RegistryObject<Block> SPECIAL_MUSHROOM_R = genBlock("fake_red_mushroom_block", new NWISNorthableBlock(PROP_MUSH));
	public static final RegistryObject<Block> FAKE_CHORUS = genBlock("fake_chorus_fruit_block", new NWISNorthableBlock(PROP_MUSH));
	
	//Grass Blocks (All Sides)
}
