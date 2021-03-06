package com.gkoliver.nwis.common.block.vegitation.swex;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.core.register.NWISBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class DoubleDoubleCropBlock extends Block implements IWaterLoggable{
	public static BooleanProperty isDone = BooleanProperty.create("grown");
	public static EnumProperty<DoubleBlockHalf> blockHalf = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private EDoubleCropType type;
	public DoubleDoubleCropBlock(Properties properties, EDoubleCropType type) {
		super(properties);
		this.type = type;
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(isDone);
		builder.add(blockHalf);
		builder.add(WATERLOGGED);
	}
	public static void generateDoubleBlock(int type, BlockPos lower_pos, BlockState i, World worldIn) {
		if (worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.AIR || worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.WATER) {
			if (type==0) {
				 BlockState newstate = null;
				 BlockState newstate_up = null;
				 if (i.get(SingleDoubleCropBlock.WATERLOGGED)) {
					 newstate = NWISBlocks.CATTAIL_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.LOWER).with(isDone, false).with(WATERLOGGED, true);
				 } else {
					 newstate = NWISBlocks.CATTAIL_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.LOWER).with(isDone, false).with(WATERLOGGED, false);
				 }
				 newstate_up = NWISBlocks.CATTAIL_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.UPPER).with(isDone, false);
				 if (worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.WATER) {
					 newstate_up = newstate_up.with(WATERLOGGED, true);
				 }
				 worldIn.setBlockState(lower_pos, newstate);
				 worldIn.setBlockState(lower_pos.up(), newstate_up);
			}
			else {
				
			}
		}
		
	}
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlockState(pos.up(), this.getDefaultState().with(blockHalf, DoubleBlockHalf.UPPER).with(isDone, false), 3);
		worldIn.setBlockState(pos, this.getDefaultState().with(blockHalf, DoubleBlockHalf.LOWER).with(isDone, false), 3);
	}
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		boolean isWater = state.get(WATERLOGGED);
		if (!player.isCrouching()) {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
		}
		if (!worldIn.isRemote()) {
			NotWhatItSeems.Triggers.CROP_CHANGES.trigger((ServerPlayerEntity)player);
		}
		if (state.get(isDone)) {
			if (state.get(blockHalf)==DoubleBlockHalf.UPPER) {
				BlockPos newPos = pos.down();
				BlockState stateHere = null;
				BlockState newerState = null;
				if (isWater) {
					stateHere = Blocks.WATER.getDefaultState();
					newerState = NWISBlocks.CATTAIL_SPROUT.get().getDefaultState().with(CropSproutBlock.WATERLOGGED, true);
				} else {
					stateHere = Blocks.AIR.getDefaultState();
					newerState = NWISBlocks.CATTAIL_SPROUT.get().getDefaultState();
				}
				
				
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			} else {
				BlockState stateHere = null;
				BlockState newerState = Blocks.AIR.getDefaultState();
				BlockPos newPos = pos.up();
				isWater = worldIn.getBlockState(newPos).get(WATERLOGGED);
				if (isWater) {
					newerState = Blocks.WATER.getDefaultState();
				} else {
					newerState = Blocks.AIR.getDefaultState();
				}
				isWater = worldIn.getBlockState(pos).get(WATERLOGGED);
				if (isWater) {
					stateHere = NWISBlocks.CATTAIL_SPROUT.get().getDefaultState().with(CropSproutBlock.WATERLOGGED, true);
				} else {
					stateHere = NWISBlocks.CATTAIL_SPROUT.get().getDefaultState();
				}
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			}
			
		} else {
			if (state.get(blockHalf)==DoubleBlockHalf.UPPER) {
				BlockPos newPos = pos.down();
				BlockState stateHere = state.with(isDone, true);
				BlockState newerState = worldIn.getBlockState(newPos).with(isDone, true);
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			} else {
				BlockPos newPos = pos.up();
				BlockState stateHere = state.with(isDone, true);
				BlockState newerState = worldIn.getBlockState(newPos).with(isDone, true);
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			}
		}
		return ActionResultType.SUCCESS;
	}
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		System.out.println("yooo");
	      DoubleBlockHalf doubleblockhalf = state.get(blockHalf);
	      BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
	      BlockState blockstate = worldIn.getBlockState(blockpos);
	      if (blockstate.getBlock() == this && blockstate.get(blockHalf) != doubleblockhalf) {
	         worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
	         worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
	      }

	      super.onBlockHarvested(worldIn, pos, state, player);
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
}
