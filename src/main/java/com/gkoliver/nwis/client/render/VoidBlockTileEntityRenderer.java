package com.gkoliver.nwis.client.render;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import com.gkoliver.nwis.common.tile.RestrainedVoidTileEntity;
import com.gkoliver.nwis.common.tile.VoidTileEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.EndPortalTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class VoidBlockTileEntityRenderer extends EndPortalTileEntityRenderer<VoidTileEntity> {
	/*public static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
	   public static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
	   private static final Random RANDOM = new Random(31100L);
	   private static final List<RenderType> RENDER_TYPES = IntStream.range(0, 16).mapToObj((p_228882_0_) -> {
	      return RenderType.getEndPortal(p_228882_0_ + 1);
	   }).collect(ImmutableList.toImmutableList());*/
	public VoidBlockTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}
	
	@Override
	public void render(VoidTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		super.render(tileEntityIn, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}
	@Override
	protected int getPasses(double p_191286_1_) {
	      return super.getPasses(p_191286_1_) + 1;
	}
	
	protected float getOffset() {
	      return 1F;
	}
	
	
	
	
	

}
