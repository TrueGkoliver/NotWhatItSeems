package com.gkoliver.nwis.common.block.vegitation.swex.pickelreedtype;

import net.minecraft.block.Block;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;

public class PickelreedDoubleBlock extends Block implements IWaterLoggable {
	public PickelreedDoubleBlock(Properties properties) {
		super(properties);
	}

	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
}
