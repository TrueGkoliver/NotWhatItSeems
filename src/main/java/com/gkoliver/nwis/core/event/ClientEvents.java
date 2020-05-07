package com.gkoliver.nwis.core.event;
import java.util.Iterator;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.client.render.VoidBlockTileEntityRenderer;
import com.gkoliver.nwis.common.gui.ImposterScreen;
import com.gkoliver.nwis.core.keybind.InverseKeybind;
import com.gkoliver.nwis.core.register.BlockRegistry;
import com.gkoliver.nwis.core.register.TileEntityRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StemBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid=NotWhatItSeems.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEvents {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		Iterator<Block> iter = BlockRegistry.CUTOUTS.iterator();
		while (iter.hasNext()) {
			Block i = iter.next();
			RenderTypeLookup.setRenderLayer(i, RenderType.getCutoutMipped());
		}
		RenderTypeLookup.setRenderLayer(BlockRegistry.STATIC_GRASS.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegistry.RESTRAINED_DILLUTED_VOID_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegistry.DILLUTED_VOID_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegistry.FAKE_BIG_GLOWSHROOM.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegistry.FAKE_BIG_GLOWSHROOM_STEM.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegistry.FAKE_BIG_GLOWSHROOM_STEM.get(), RenderType.getTranslucent());
		ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.VOID_BLOCK.get(), VoidBlockTileEntityRenderer::new);
		ScreenManager.registerFactory(TileEntityRegistry.COPIER.get(), ImposterScreen::new);
		InverseKeybind.addKey();
	}
	@SubscribeEvent
	public static void onColorBlockRegister(ColorHandlerEvent.Block event) {
		event.getBlockColors().register((p_228057_0_, p_228057_1_, p_228057_2_, p_228057_3_) -> {
	        return 14731036;
	    }, BlockRegistry.FAKE_MELON_STEM_ATTACHED.get(), BlockRegistry.FAKE_PUMPKIN_STEM_ATTACHED.get());
		event.getBlockColors().register((p_228056_0_, p_228056_1_, p_228056_2_, p_228056_3_) -> {
	         int i = p_228056_0_.get(StemBlock.AGE);
	         int j = i * 32;
	         int k = 255 - i * 8;
	         int l = i * 4;
	         return j << 16 | k << 8 | l;
	      }, BlockRegistry.FAKE_MELON_STEM.get(), BlockRegistry.FAKE_PUMPKIN_STEM.get());
		
		event.getBlockColors().register((p_228061_0_, p_228061_1_, p_228061_2_, p_228061_3_) -> {
	         return p_228061_1_ != null && p_228061_2_ != null ? BiomeColors.getFoliageColor(p_228061_1_, p_228061_2_) : FoliageColors.getDefault();
	      }, BlockRegistry.FAKE_VINE.get());
		
		event.getBlockColors().register((p_228064_0_, p_228064_1_, p_228064_2_, p_228064_3_) -> {
	         return p_228064_1_ != null && p_228064_2_ != null ? BiomeColors.getGrassColor(p_228064_1_, p_228064_2_) : GrassColors.get(0.5D, 1.0D);
	    }, BlockRegistry.STATIC_GRASS.get(), BlockRegistry.STATIC_GRASS_A.get());
	}
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.VOID_BLOCK.get(), VoidBlockTileEntityRenderer::new);
	}
	
	
}
