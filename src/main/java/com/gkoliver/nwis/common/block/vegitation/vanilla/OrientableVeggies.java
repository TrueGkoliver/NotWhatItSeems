package com.gkoliver.nwis.common.block.vegitation.vanilla;

import java.util.Map;

import com.gkoliver.nwis.common.block.other.NWISOrientableBlock;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
//This is really just used for shapes, tbh.
public class OrientableVeggies extends NWISOrientableBlock {
	/*private static final Map<Direction, VoxelShape> SHAPES_GRASS = Maps.newEnumMap(
			ImmutableMap.of(
					Direction.NORTH, Block.makeCuboidShape(0.0D, 4.0D, 5.0D, 16.0D, 12.0D, 16.0D), 
					Direction.SOUTH, Block.makeCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 12.0D, 11.0D), 
					Direction.WEST, Block.makeCuboidShape(5.0D, 4.0D, 0.0D, 16.0D, 12.0D, 16.0D), 
					Direction.EAST, Block.makeCuboidShape(0.0D, 4.0D, 0.0D, 11.0D, 12.0D, 16.0D),
					Direction.UP, Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
					Direction.DOWN, Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D)
					)
			);*/
	
	
	public OrientableVeggies(Properties properties) {
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return super.getShape(state, worldIn, pos, context);
	}

}
