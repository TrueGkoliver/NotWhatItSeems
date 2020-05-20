package com.gkoliver.nwis.common.block.vegitation.swex.rice;

import com.gkoliver.nwis.NotWhatItSeems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class RiceSingleSingleBlock extends Block {
	public static IntegerProperty age = IntegerProperty.create("age", 0, 5);
	public RiceSingleSingleBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(age);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.isShiftKeyDown()) {
			if (!worldIn.isRemote()) {
				NotWhatItSeems.Triggers.CROP_CHANGES.trigger((ServerPlayerEntity)player);
			}
			Integer age2 = state.get(age);
			if (age2==5) {
				RiceDoubleSingleBlock.generateDoubleBlock(0, pos, state, worldIn);
			} else {
				worldIn.setBlockState(pos, state.with(age, age2+1));
			}
		}
		return ActionResultType.SUCCESS;
	}

}
