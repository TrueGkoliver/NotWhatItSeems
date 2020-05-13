package com.gkoliver.nwis.common.block.other;

import com.gkoliver.nwis.common.tile.RestrainedVoidTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class RestrainedVoidBlock extends ContainerBlock {
	public static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 12, 16);
	public RestrainedVoidBlock(Properties properties) {
		super(properties);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new RestrainedVoidTileEntity();
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

}
