package com.gkoliver.nwis.common.block.vegitation;

import com.gkoliver.nwis.common.block.other.NWISNorthableBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SemiInvisibleBlock extends Block {

	public SemiInvisibleBlock(Properties properties) {
		super(properties);
	}
	@OnlyIn(value=Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState bs1, BlockState bs2, Direction side) {
		return super.isSideInvisible(bs1, bs2, side) || bs2.getBlock() == this ;
	}

}
