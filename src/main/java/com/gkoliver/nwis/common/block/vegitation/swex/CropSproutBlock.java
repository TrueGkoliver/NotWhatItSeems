package com.gkoliver.nwis.common.block.vegitation.swex;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
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

public class CropSproutBlock extends Block implements IWaterLoggable {
	public static BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public CropSproutBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.isShiftKeyDown()) {
			if (!worldIn.isRemote()) {
				NotWhatItSeems.Triggers.CROP_CHANGES.trigger((ServerPlayerEntity)player);
			}
			if (state.get(WATERLOGGED)) {
				worldIn.setBlockState(pos, BlockRegistry.CATTAIL.get().getDefaultState().with(SingleDoubleCropBlock.WATERLOGGED, true));
				return ActionResultType.SUCCESS;
			}
			worldIn.setBlockState(pos, BlockRegistry.CATTAIL.get().getDefaultState());
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
