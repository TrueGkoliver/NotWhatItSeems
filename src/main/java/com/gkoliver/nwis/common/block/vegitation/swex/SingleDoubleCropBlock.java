package com.gkoliver.nwis.common.block.vegitation.swex;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
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
	
	

}
