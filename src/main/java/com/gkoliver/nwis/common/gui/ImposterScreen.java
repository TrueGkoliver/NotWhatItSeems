package com.gkoliver.nwis.common.gui;

import com.gkoliver.nwis.NotWhatItSeems;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.GrindstoneContainer;
import net.minecraft.inventory.container.HopperContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ImposterScreen extends ContainerScreen<ImposterContainer> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(NotWhatItSeems.MODID,"textures/gui/container/copier.png");

	   /*public ImposterScreen(ImposterContainer container, PlayerInventory inv, ITextComponent component) {
	      super(container, inv, component);
	   }*/
	   
	   public ImposterScreen(ImposterContainer p_i51085_1_, PlayerInventory p_i51085_2_, ITextComponent p_i51085_3_) {
		      super(p_i51085_1_, p_i51085_2_, p_i51085_3_);
		      this.passEvents = false;
		      this.ySize = 133;
		      this.field_238745_s_ = this.ySize - 94;
		   }
			@Override
		   public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		      this.renderBackground(p_230430_1_);
		      super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		      this.func_230459_a_(p_230430_1_, p_230430_2_, p_230430_3_);
		   }

		   protected void func_230450_a_(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		      this.minecraft.getTextureManager().bindTexture(TEXTURE);
		      int i = (this.width - this.xSize) / 2;
		      int j = (this.height - this.ySize) / 2;
		      this.blit(p_230450_1_, i, j, 0, 0, this.xSize, this.ySize);
		   }

}
