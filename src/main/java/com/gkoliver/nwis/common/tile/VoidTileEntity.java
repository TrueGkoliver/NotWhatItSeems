package com.gkoliver.nwis.common.tile;

import com.gkoliver.nwis.core.register.TileEntityRegistry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VoidTileEntity extends EndPortalTileEntity {

	public VoidTileEntity() {
		super(TileEntityRegistry.VOID_BLOCK.get());
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean shouldRenderFace(Direction face) {
		return true;
	      //return Block.shouldSideBeRendered(this.getBlockState(), this.world, this.getPos(), face);
	}

}
