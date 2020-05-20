package com.gkoliver.nwis.common.block.crop;

import com.gkoliver.nwis.NotWhatItSeems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class FakeCocoaBeanBlock extends Block implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_2;
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	//Shapes
	protected static final VoxelShape[] COCOA_EAST_AABB = new VoxelShape[]{Block.makeCuboidShape(11.0D, 7.0D, 6.0D, 15.0D, 12.0D, 10.0D), Block.makeCuboidShape(9.0D, 5.0D, 5.0D, 15.0D, 12.0D, 11.0D), Block.makeCuboidShape(7.0D, 3.0D, 4.0D, 15.0D, 12.0D, 12.0D)};
	protected static final VoxelShape[] COCOA_WEST_AABB = new VoxelShape[]{Block.makeCuboidShape(1.0D, 7.0D, 6.0D, 5.0D, 12.0D, 10.0D), Block.makeCuboidShape(1.0D, 5.0D, 5.0D, 7.0D, 12.0D, 11.0D), Block.makeCuboidShape(1.0D, 3.0D, 4.0D, 9.0D, 12.0D, 12.0D)};
	protected static final VoxelShape[] COCOA_NORTH_AABB = new VoxelShape[]{Block.makeCuboidShape(6.0D, 7.0D, 1.0D, 10.0D, 12.0D, 5.0D), Block.makeCuboidShape(5.0D, 5.0D, 1.0D, 11.0D, 12.0D, 7.0D), Block.makeCuboidShape(4.0D, 3.0D, 1.0D, 12.0D, 12.0D, 9.0D)};
	protected static final VoxelShape[] COCOA_SOUTH_AABB = new VoxelShape[]{Block.makeCuboidShape(6.0D, 7.0D, 11.0D, 10.0D, 12.0D, 15.0D), Block.makeCuboidShape(5.0D, 5.0D, 9.0D, 11.0D, 12.0D, 15.0D), Block.makeCuboidShape(4.0D, 3.0D, 7.0D, 12.0D, 12.0D, 15.0D)};
	
	
	public FakeCocoaBeanBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(AGE, 0).with(WATERLOGGED, false));
	}
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(AGE);
	      builder.add(WATERLOGGED);
	      builder.add(HORIZONTAL_FACING);
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int i = state.get(AGE);
	      switch((Direction)state.get(HORIZONTAL_FACING)) {
	      case SOUTH:
	         return COCOA_SOUTH_AABB[i];
	      case NORTH:
	      default:
	         return COCOA_NORTH_AABB[i];
	      case WEST:
	         return COCOA_WEST_AABB[i];
	      case EAST:
	         return COCOA_EAST_AABB[i];
	     }
	}
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.isShiftKeyDown()) {
			if (!worldIn.isRemote()) {
				NotWhatItSeems.Triggers.CROP_CHANGES.trigger((ServerPlayerEntity)player);
			}
			if (state.get(AGE) == 2) {
				worldIn.setBlockState(pos, state.with(AGE, 0));
			} else {
				worldIn.setBlockState(pos, state.with(AGE, state.get(AGE)+1));
			}
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
	}
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
	}
	
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
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

}
