package com.gkoliver.nwis.common.block;

import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class NWISBlock extends Block {
	protected static final VoxelShape SAPLING_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	public NWISBlock(Properties properties) {
		super(properties);
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean isSapling = (state.getBlock() == BlockRegistry.FAKE_OAK_SAPLING.get() || 
				state.getBlock() == BlockRegistry.FAKE_BIRCH_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FAKE_SPRUCE_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FAKE_JUNGLE_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FAKE_ACACIA_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FAKE_DARK_OAK_SAPLING.get());
		if (isSapling) {
			return SAPLING_SHAPE;
		}
		return Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
	}
}
