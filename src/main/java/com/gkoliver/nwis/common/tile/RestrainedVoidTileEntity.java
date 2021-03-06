package com.gkoliver.nwis.common.tile;

import com.gkoliver.nwis.core.register.NWISTileEntities;

import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RestrainedVoidTileEntity extends EndPortalTileEntity {

	public RestrainedVoidTileEntity() {
		super(NWISTileEntities.RESTAINED_VOID_BLOCK.get());
	}
	@OnlyIn(Dist.CLIENT)
	public boolean shouldRenderFace(Direction face) {
		return true;
	}

}
