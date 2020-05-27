package com.gkoliver.nwis.common.block.vegitation.vanilla;

import com.gkoliver.nwis.core.util.SharedFunctions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class TallOrientableBlock extends OrientableVeggies {
	ETallTypes tall_type;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public TallOrientableBlock(ETallTypes type, Properties properties) {
		super(EOrientables.GRASS, properties);
		this.setDefaultState(this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
		tall_type = type;
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(HALF);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getFace());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		Direction d = state.get(FACING);
		BlockPos poggers = SharedFunctions.getOppositePos(pos, false, d);
		worldIn.setBlockState(poggers, this.getDefaultState().with(FACING, d).with(WATERLOGGED, false).with(HALF, DoubleBlockHalf.UPPER));
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		Direction d = state.get(FACING);
		boolean isHeight = state.get(HALF) == DoubleBlockHalf.UPPER;
		BlockPos poggers = SharedFunctions.getOppositePos(pos, isHeight, d);
		BlockState cool = worldIn.getBlockState(poggers);
		if (cool.getBlock() == this) {
			if (worldIn.getBlockState(poggers).get(WATERLOGGED)) {
				worldIn.setBlockState(poggers, Blocks.WATER.getDefaultState());
			} else {
				worldIn.setBlockState(poggers, Blocks.AIR.getDefaultState());
			}
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
	}
	
	@SuppressWarnings("deprecation")
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      if (state.get(HALF) != DoubleBlockHalf.UPPER) {
	    	  BlockState otherBlock = worldIn.getBlockState(SharedFunctions.getOppositePos(pos, false, state.get(FACING)));
	    	  if (otherBlock.getBlock().isSolid(otherBlock)) {
	    		  return false;
	    	  }
	    	  return true;
	      } else {
	    	  BlockState otherBlock = worldIn.getBlockState(SharedFunctions.getOppositePos(pos, true, state.get(FACING)));
	    	  if (otherBlock.getBlock().isSolid(otherBlock)) {
	    		  return false;
	    	  }
	    	  return true;
	      }
	   }
	
}
