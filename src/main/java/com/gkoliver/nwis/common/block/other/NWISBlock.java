package com.gkoliver.nwis.common.block.other;

import com.gkoliver.nwis.core.register.NWISBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class NWISBlock extends Block {
	protected static final VoxelShape SAPLING_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	protected static final VoxelShape MUSHROOM_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	protected static final VoxelShape EDRAG_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	public boolean waterlog = false;
	public NWISBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState());
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean isSapling = (state.getBlock() == NWISBlocks.FAKE_OAK_SAPLING.get() ||
				state.getBlock() == NWISBlocks.FAKE_BIRCH_SAPLING.get() ||
				state.getBlock() == NWISBlocks.FAKE_SPRUCE_SAPLING.get() ||
				state.getBlock() == NWISBlocks.FAKE_JUNGLE_SAPLING.get() ||
				state.getBlock() == NWISBlocks.FAKE_ACACIA_SAPLING.get() ||
				state.getBlock() == NWISBlocks.FAKE_DARK_OAK_SAPLING.get() ||
				state.getBlock() == NWISBlocks.FROSTY_SAPLING.get() ||
				state.getBlock() == NWISBlocks.MAPLE_SAPLING.get() ||
				state.getBlock() == NWISBlocks.MAPLE_SAPLING_ORANGE.get() ||
				state.getBlock() == NWISBlocks.MAPLE_SAPLING_RED.get() ||
				state.getBlock() == NWISBlocks.ROSEWOOD_SAPLING.get() ||
				state.getBlock() == NWISBlocks.YUCCA_SAPLING.get() ||
				state.getBlock() == NWISBlocks.ASPEN_SAPLING.get() ||
				state.getBlock() == NWISBlocks.KOUSA_SAPLING.get() ||
				state.getBlock() == NWISBlocks.SERENE_SAPLING.get() ||
				state.getBlock() == NWISBlocks.SUNNY_SAPLING.get() ||
				state.getBlock() == NWISBlocks.SWEET_SAPLING.get() ||
				state.getBlock() == NWISBlocks.WARM_SAPLING.get() ||
				state.getBlock() == NWISBlocks.WISTERIA_BLUE.get() ||
				state.getBlock() == NWISBlocks.WISTERIA_PINK.get() ||
				state.getBlock() == NWISBlocks.WISTERIA_PURPLE.get() ||
				state.getBlock() == NWISBlocks.WISTERIA_WHITE.get());
		boolean isMushroom = (state.getBlock() == NWISBlocks.FAKE_MUSHOOM_1.get() ||
				state.getBlock() == NWISBlocks.FAKE_MUSHOOM_2.get() ||
				state.getBlock() == NWISBlocks.FAKE_GLOWSHROOM.get());
		if (isSapling) {
			return SAPLING_SHAPE;
		} else if (isMushroom) {
			return MUSHROOM_SHAPE;
		}
		return Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
	}
}
