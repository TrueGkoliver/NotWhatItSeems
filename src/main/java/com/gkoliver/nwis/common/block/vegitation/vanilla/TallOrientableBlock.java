package com.gkoliver.nwis.common.block.vegitation.vanilla;

import com.gkoliver.nwis.core.util.SharedFunctions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
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
	
}
