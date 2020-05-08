package com.gkoliver.nwis.common.block.vegitation.swex;

import com.gkoliver.nwis.core.register.BlockRegistry;
import com.google.common.util.concurrent.Service.State;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

public class DoubleDoubleCropBlock extends Block {
	public static BooleanProperty isDone = BooleanProperty.create("grown");
	public static EnumProperty<DoubleBlockHalf> blockHalf = BlockStateProperties.DOUBLE_BLOCK_HALF;
	private EDoubleCropType type;
	public DoubleDoubleCropBlock(Properties properties, EDoubleCropType type) {
		super(properties);
		this.type = type;
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(isDone);
		builder.add(blockHalf);
	}
	public static void generateDoubleBlock(int type, BlockPos lower_pos, BlockState i, World worldIn) {
		if (worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.AIR) {
			if (type==0) {
				 BlockState newstate = BlockRegistry.CATTAIL_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.LOWER).with(isDone, false);
				 BlockState newstate_up = BlockRegistry.CATTAIL_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.UPPER).with(isDone, false);
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
