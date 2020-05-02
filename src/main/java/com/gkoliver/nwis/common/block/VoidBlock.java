package com.gkoliver.nwis.common.block;

import com.gkoliver.nwis.common.tile.VoidTileEntity;

import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class VoidBlock extends ContainerBlock {

	public VoidBlock(Properties builder) {
		super(builder);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		System.out.println("Get the zoom");
		return new VoidTileEntity();
	}

}
