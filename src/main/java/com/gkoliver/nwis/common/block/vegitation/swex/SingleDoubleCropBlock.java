package com.gkoliver.nwis.common.block.vegitation.swex;

import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class SingleDoubleCropBlock extends Block {

	private EDoubleCropType type;
	
	public SingleDoubleCropBlock(Properties properties, EDoubleCropType type) {
		super(properties);
		this.type = type;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (type == EDoubleCropType.CATTAIL) {
			DoubleDoubleCropBlock.generateDoubleBlock(0, pos, state, player.world);
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (state.get(isDone)) {
			if (state.get(blockHalf)==DoubleBlockHalf.UPPER) {
				BlockPos newPos = pos.down();
				BlockState stateHere = Blocks.AIR.getDefaultState();
				BlockState newerState = BlockRegistry.CATTAIL_SPROUT.get().getDefaultState();
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			} else {
				BlockPos newPos = pos.up();
				BlockState stateHere = BlockRegistry.CATTAIL_SPROUT.get().getDefaultState();
				BlockState newerState = Blocks.AIR.getDefaultState();
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			}
			
		} else {
			if (state.get(blockHalf)==DoubleBlockHalf.UPPER) {
				BlockPos newPos = pos.down();
				BlockState stateHere = worldIn.getBlockState(pos).with(isDone, true);
				BlockState newerState = worldIn.getBlockState(newPos).with(isDone, true);
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			} else {
				BlockPos newPos = pos.up();
				BlockState stateHere = worldIn.getBlockState(pos).with(isDone, true);
				BlockState newerState = worldIn.getBlockState(newPos).with(isDone, true);
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			}
		}
		return ActionResultType.SUCCESS;
	}

}
