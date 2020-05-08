package com.gkoliver.nwis.common.block.vegitation.swex;

import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CropSproutBlock extends Block {

	public CropSproutBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.isShiftKeyDown()) {
			worldIn.setBlockState(pos, BlockRegistry.CATTAIL.get().getDefaultState());
		}
		return ActionResultType.SUCCESS;
	}
	

}
