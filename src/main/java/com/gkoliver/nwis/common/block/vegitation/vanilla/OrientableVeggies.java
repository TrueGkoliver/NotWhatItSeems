package com.gkoliver.nwis.common.block.vegitation.vanilla;

import java.util.HashMap;
import java.util.Map;

import com.gkoliver.nwis.common.block.other.NWISOrientableBlock;
import com.gkoliver.nwis.core.util.SharedFunctions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
//This is really just used for shapes, tbh.
public class OrientableVeggies extends NWISOrientableBlock implements IWaterLoggable {
	private static final HashMap<Direction, VoxelShape> GRASS_SHAPES = SharedFunctions.makeShapeList(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private static final HashMap<Direction, VoxelShape> SHROOM_SHAPES = SharedFunctions.makeShapeList(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	private static final HashMap<Direction, VoxelShape> DEAD_SHAPES = SharedFunctions.makeShapeList(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	EOrientables type;
	public OrientableVeggies(EOrientables type, Properties properties) {
		super(properties);
		this.type = type;
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(WATERLOGGED);
	}
	
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		return IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
	}

	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn);
	}
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
	
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (type==EOrientables.GRASS) {
			return GRASS_SHAPES.get(state.get(FACING));
		} else if (type==EOrientables.MUSHROOM) {
			return SHROOM_SHAPES.get(state.get(FACING));
		} else if (type==EOrientables.DEAD_BUSH) {
			return DEAD_SHAPES.get(state.get(FACING));
		}
		return super.getShape(state, worldIn, pos, context);
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
	

}
