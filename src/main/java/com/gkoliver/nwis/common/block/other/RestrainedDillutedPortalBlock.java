package com.gkoliver.nwis.common.block.other;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RestrainedDillutedPortalBlock extends Block implements IWaterLoggable {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public RestrainedDillutedPortalBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED,false));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch((Direction.Axis)state.get(AXIS)) {
	      case Z:
	         return Z_AABB;
	      case X:
	      default:
	         return X_AABB;
	    }
	}
	
	/*@Override
	@OnlyIn(value=Dist.CLIENT)
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
		if (adjacentBlockState.getBlock() == this) {
			System.out.println(state.get(IDEAL) && adjacentBlockState.get(IDEAL));
			return (state.get(IDEAL) && adjacentBlockState.get(IDEAL))||super.isSideInvisible(state, adjacentBlockState, side);
		} else {
			return super.isSideInvisible(state, adjacentBlockState, side);
		}
		
	}*/
	
	
	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
		return (adjacentBlockState.getBlock()==this)||super.isSideInvisible(state, adjacentBlockState, side);
	}
	/*@OnlyIn(value=Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState bs1, BlockState bs2, Direction side) {
		if (bs2.getBlock() == this) {
			boolean x = bs2.get(AXIS) == bs1.get(AXIS);
			if (x) {
				Axis axis = bs2.get(AXIS);
				if (axis == Axis.X) {
					//TODO fix this nonsense
				} else {
					//TODO fix this
				}
			} else {
				return false;
			}
		} else {
			return super.isSideInvisible(bs1, bs2, side);
		}
		return super.isSideInvisible(bs1, bs2, side);
	}*/
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(AXIS, context.getPlacementHorizontalFacing().getAxis());
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(AXIS);
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
}
