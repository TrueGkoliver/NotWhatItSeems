package com.gkoliver.nwis.common.block.vegitation.swex.rice;

import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class RiceDoubleSingleBlock extends Block {
	public static EnumProperty<DoubleBlockHalf> blockHalf = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static IntegerProperty age = IntegerProperty.create("age", 6, 7);
	public RiceDoubleSingleBlock(Properties properties) {
		super(properties);
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(blockHalf);
		builder.add(age);
	}
	public static void generateDoubleBlock(int type, BlockPos lower_pos, BlockState i, World worldIn) {
		if (worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.AIR) {
			if (type==0) {
				 BlockState newstate = BlockRegistry.RICE_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.LOWER).with(age, 6);
				 BlockState newstate_up = BlockRegistry.RICE_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.UPPER).with(age, 6);
				 worldIn.setBlockState(lower_pos, newstate);
				 worldIn.setBlockState(lower_pos.up(), newstate_up);
			}
			else {
				
			}
		}
		
	}
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		boolean bool = state.get(age) == 7;
		if (bool) {
			if (state.get(blockHalf)==DoubleBlockHalf.UPPER) {
				BlockPos newPos = pos.down();
				BlockState stateHere = Blocks.AIR.getDefaultState();
				BlockState newerState = BlockRegistry.RICE.get().getDefaultState();
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			} else {
				BlockPos newPos = pos.up();
				BlockState stateHere = BlockRegistry.RICE.get().getDefaultState();
				BlockState newerState = Blocks.AIR.getDefaultState();
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			}
			
		} else {
			if (state.get(blockHalf)==DoubleBlockHalf.UPPER) {
				BlockPos newPos = pos.down();
				BlockState stateHere = worldIn.getBlockState(pos).with(age, 7);
				BlockState newerState = worldIn.getBlockState(newPos).with(age, 7);
				worldIn.setBlockState(pos, stateHere);
				worldIn.setBlockState(newPos, newerState);
			} else {
				BlockPos newPos = pos.up();
				BlockState stateHere = worldIn.getBlockState(pos).with(age, 7);
				BlockState newerState = worldIn.getBlockState(newPos).with(age, 7);
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
	
	
	

}
