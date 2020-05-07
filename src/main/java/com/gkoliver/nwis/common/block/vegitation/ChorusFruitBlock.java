package com.gkoliver.nwis.common.block.vegitation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class ChorusFruitBlock extends Block {
	public static final BooleanProperty GROWN = BooleanProperty.create("grown");
	public ChorusFruitBlock(Properties properties) {
		super(properties);
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(GROWN);
	}
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		
		if (player.isShiftKeyDown()) {
			worldIn.setBlockState(pos, state.with(GROWN, !state.get(GROWN)));
		}
		return ActionResultType.SUCCESS;
	}

}
