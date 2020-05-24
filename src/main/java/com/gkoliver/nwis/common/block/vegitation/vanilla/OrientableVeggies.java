package com.gkoliver.nwis.common.block.vegitation.vanilla;

import java.util.HashMap;
import java.util.Map;

import com.gkoliver.nwis.common.block.other.NWISOrientableBlock;
import com.gkoliver.nwis.core.util.SharedFunctions;
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
	private static final HashMap<Direction, VoxelShape> GRASS_SHAPES = SharedFunctions.makeShapeList(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	
	EOrientables type;
	public OrientableVeggies(EOrientables type, Properties properties) {
		super(properties);
		this.type = type;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (type==EOrientables.GRASS) {
			return GRASS_SHAPES.get(state.get(FACING));
		}
		return super.getShape(state, worldIn, pos, context);
	}

}
