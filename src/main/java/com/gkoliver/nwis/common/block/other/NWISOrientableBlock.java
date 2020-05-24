package com.gkoliver.nwis.common.block.other;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;

public class NWISOrientableBlock extends Block {
	public NWISOrientableBlock(Properties properties) {
		super(properties);
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getFace());
	}
}
