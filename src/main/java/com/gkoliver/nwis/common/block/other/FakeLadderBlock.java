package com.gkoliver.nwis.common.block.other;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class FakeLadderBlock extends Block implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public static final BooleanProperty UP = SixWayBlock.UP;
	public static final BooleanProperty DOWN = SixWayBlock.DOWN;
    public static final BooleanProperty NORTH = SixWayBlock.NORTH;
    public static final BooleanProperty EAST = SixWayBlock.EAST;
    public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
    public static final BooleanProperty WEST = SixWayBlock.WEST;
	
	public FakeLadderBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false)
				.with(UP, false)
				.with(DOWN, false)
				.with(NORTH, false)
				.with(SOUTH, false)
				.with(EAST, false)
				.with(WEST, false));
	}
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (context.getFace()==Direction.NORTH) {
			return this.getDefaultState().with(NORTH, true);
		}
		if (context.getFace()==Direction.SOUTH) {
			return this.getDefaultState().with(SOUTH, true);
		}
		if (context.getFace()==Direction.WEST) {
			return this.getDefaultState().with(WEST, true);
		}
		if (context.getFace()==Direction.EAST) {
			return this.getDefaultState().with(EAST, true);
		}
		if (context.getFace()==Direction.UP) {
			return this.getDefaultState().with(UP, true);
		}
		if (context.getFace()==Direction.DOWN) {
			return this.getDefaultState().with(DOWN, true);
		}
		return super.getStateForPlacement(context);
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
		builder.add(UP);
		builder.add(DOWN);
		builder.add(NORTH);
		builder.add(SOUTH);
		builder.add(WEST);
		builder.add(EAST);
	}
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (player.isCrouching()) {
			BlockState state2 = null;
			if (hit.getFace()==Direction.NORTH) {
				state2 = state.with(NORTH, true);
			}
			if (hit.getFace()==Direction.SOUTH) {
				state2 = state.with(SOUTH, true);
			}
			if (hit.getFace()==Direction.UP) {
				state2 = state.with(UP, true);
			}
			if (hit.getFace()==Direction.DOWN) {
				state2 = state.with(DOWN, true);
			}
			if (hit.getFace()==Direction.WEST) {
				state2 = state.with(WEST, true);
			}
			if (hit.getFace()==Direction.EAST) {
				state2 = state.with(EAST, true);
			}
			worldIn.setBlockState(pos, state2);
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
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
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

}
