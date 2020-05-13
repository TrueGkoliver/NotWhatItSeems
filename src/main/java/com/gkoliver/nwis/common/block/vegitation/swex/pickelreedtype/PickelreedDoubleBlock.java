package com.gkoliver.nwis.common.block.vegitation.swex.pickelreedtype;

import com.gkoliver.nwis.common.block.vegitation.swex.CropSproutBlock;
import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.Half;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class PickelreedDoubleBlock extends Block implements IWaterLoggable {
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public PickelreedDoubleBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HALF);
		builder.add(WATERLOGGED);
	}
	public static void generateDoubleBlock(boolean isWaterlogged, BlockPos lower_pos, BlockState i, World worldIn, int color) {
		if (worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.AIR || worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.WATER) {
			BlockPos higher_pos = lower_pos.up();
			BlockState downState = null; 
			BlockState upState = null;
			if (color==0) {
				downState = BlockRegistry.PICKELREED_BLUE_BIG.get().getDefaultState().with(HALF, DoubleBlockHalf.LOWER);
				upState = BlockRegistry.PICKELREED_BLUE_BIG.get().getDefaultState().with(HALF, DoubleBlockHalf.UPPER);
			} else {
				downState = BlockRegistry.PICKELREED_PURPLE_BIG.get().getDefaultState().with(HALF, DoubleBlockHalf.LOWER);
				upState = BlockRegistry.PICKELREED_PURPLE_BIG.get().getDefaultState().with(HALF, DoubleBlockHalf.UPPER);
			}
			if (isWaterlogged) {
				downState = downState.with(WATERLOGGED, true);
			}
			if (worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.WATER) {
				upState = upState.with(WATERLOGGED, true);
			}
			worldIn.setBlockState(lower_pos, downState);
			worldIn.setBlockState(higher_pos, upState);
		}
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
	      DoubleBlockHalf doubleblockhalf = state.get(HALF);
	      BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
	      BlockState blockstate = worldIn.getBlockState(blockpos);
	      if (blockstate.getBlock() == this && blockstate.get(HALF) != doubleblockhalf) {
	         worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
	         worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
	      }

	      super.onBlockHarvested(worldIn, pos, state, player);
	   }
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		boolean isWater = state.get(WATERLOGGED);
		if (player.isShiftKeyDown()) {
			if (state.get(HALF)==DoubleBlockHalf.UPPER) {
				BlockPos newPos = pos.down();
				BlockState stateHere = null;
				BlockState newerState = null;
				if (this == BlockRegistry.PICKELREED_BLUE_BIG.get()) {
					newerState = BlockRegistry.PICKELREED_BLUE.get().getDefaultState();
				} else {
					newerState = BlockRegistry.PICKELREED_PURPLE.get().getDefaultState();
				}
				
				if (isWater) {
					stateHere = Blocks.WATER.getDefaultState();
					newerState = newerState.with(CropSproutBlock.WATERLOGGED, true);
				} else {
					stateHere = Blocks.AIR.getDefaultState();
				}
				
				
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			} else {
				BlockPos newPos = pos.up();
				BlockState stateHere = null;
				BlockState newerState = null;
				if (this == BlockRegistry.PICKELREED_BLUE_BIG.get()) {
					stateHere = BlockRegistry.PICKELREED_BLUE.get().getDefaultState();
				} else {
					stateHere = BlockRegistry.PICKELREED_PURPLE.get().getDefaultState();
				}
				
				if (isWater) {
					newerState = Blocks.WATER.getDefaultState();
					stateHere = newerState.with(CropSproutBlock.WATERLOGGED, true);
				} else {
					newerState = Blocks.AIR.getDefaultState();
				}
				
				
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			}
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
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
