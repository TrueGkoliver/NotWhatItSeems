package com.gkoliver.nwis.common.block.other;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SemiInvisibleNorthableBlock extends NWISNorthableBlock {

	public SemiInvisibleNorthableBlock(Properties properties) {
		super(properties);
	}
	@OnlyIn(value=Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState bs1, BlockState bs2, Direction side) {
		return super.isSideInvisible(bs1, bs2, side) || bs2.getBlock() == this ;
	}

}
