package com.gkoliver.nwis.core.event;
import java.util.HashMap;
import java.util.Iterator;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.client.render.RestrainedVoidTileEntityRenderer;
import com.gkoliver.nwis.client.render.VoidBlockTileEntityRenderer;
import com.gkoliver.nwis.common.gui.CopierScreen;
import com.gkoliver.nwis.core.keybind.InverseKeybind;
import com.gkoliver.nwis.core.register.NWISBlocks;
import com.gkoliver.nwis.core.register.NWISTileEntities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StemBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid=NotWhatItSeems.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEvents {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		Iterator<Block> iter = NWISBlocks.CUTOUTS.iterator();
		while (iter.hasNext()) {
			Block i = iter.next();
			RenderTypeLookup.setRenderLayer(i, RenderType.getCutoutMipped());
		}
		RenderTypeLookup.setRenderLayer(NWISBlocks.STATIC_GRASS.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NWISBlocks.RESTRAINED_DILLUTED_VOID_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(NWISBlocks.RESTRAINED_DILLUTED_VOID_BLOCK_SEMI.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(NWISBlocks.DILLUTED_VOID_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(NWISBlocks.DILLUTED_VOID_BLOCK_SEMISOLID.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(NWISBlocks.FAKE_BIG_GLOWSHROOM.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(NWISBlocks.FAKE_BIG_GLOWSHROOM_STEM.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(NWISBlocks.FAKE_BIG_GLOWSHROOM_STEM.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(NWISBlocks.STATIC_POISE_CLUSTER.get(), RenderType.getTranslucent());
		ClientRegistry.bindTileEntityRenderer(NWISTileEntities.VOID_BLOCK.get(), VoidBlockTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(NWISTileEntities.RESTAINED_VOID_BLOCK.get(), RestrainedVoidTileEntityRenderer::new);
		ScreenManager.registerFactory(NWISTileEntities.COPIER.get(), CopierScreen::new);
		InverseKeybind.addKey();
	}
	@SubscribeEvent
	public static void onColorBlockRegister(ColorHandlerEvent.Block event) {
		event.getBlockColors().register((p_228057_0_, p_228057_1_, p_228057_2_, p_228057_3_) -> {
	        return 14731036;
	    }, NWISBlocks.FAKE_MELON_STEM_ATTACHED.get(), NWISBlocks.FAKE_PUMPKIN_STEM_ATTACHED.get());
		event.getBlockColors().register((p_228056_0_, p_228056_1_, p_228056_2_, p_228056_3_) -> {
	         int i = p_228056_0_.get(StemBlock.AGE);
	         int j = i * 32;
	         int k = 255 - i * 8;
	         int l = i * 4;
	         return j << 16 | k << 8 | l;
	      }, NWISBlocks.FAKE_MELON_STEM.get(), NWISBlocks.FAKE_PUMPKIN_STEM.get());
		
		event.getBlockColors().register((p_228061_0_, p_228061_1_, p_228061_2_, p_228061_3_) -> {
	         return p_228061_1_ != null && p_228061_2_ != null ? BiomeColors.getFoliageColor(p_228061_1_, p_228061_2_) : FoliageColors.getDefault();
	      }, NWISBlocks.FAKE_VINE.get());
		
		event.getBlockColors().register((p_228064_0_, p_228064_1_, p_228064_2_, p_228064_3_) -> {
	         return p_228064_1_ != null && p_228064_2_ != null ? BiomeColors.getGrassColor(p_228064_1_, p_228064_2_) : GrassColors.get(0.5D, 1.0D);
	    }, NWISBlocks.STATIC_GRASS.get(), NWISBlocks.STATIC_GRASS_A.get(), NWISBlocks.FAKE_TALL_GRASS.get(), NWISBlocks.FAKE_GRASS.get(), NWISBlocks.FAKE_TALL_FERN.get(), NWISBlocks.FAKE_FERN.get());
	}
	public static HashMap<BlockItem, Integer> COLOR_MAPS = new HashMap<BlockItem, Integer>();
	@SubscribeEvent
	public static void onColorItemRegister(ColorHandlerEvent.Item event) {
		event.getItemColors().register((itemstack, p_210235_2_) -> {
			
	        BlockState blockstate = ((BlockItem)itemstack.getItem()).getBlock().getDefaultState();
	        return event.getBlockColors().getColor(blockstate, (IBlockDisplayReader)null, (BlockPos)null, p_210235_2_);
	   }, Item.getItemFromBlock(NWISBlocks.STATIC_GRASS.get()), Item.getItemFromBlock(NWISBlocks.STATIC_GRASS_A.get()));
		for (BlockItem item : COLOR_MAPS.keySet()) {
			int blocker = COLOR_MAPS.get(item);
			event.getItemColors().register((p_210235_1_, p_210235_2_) -> {
		         return blocker;
		      }, item);
		}
	}
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		ClientRegistry.bindTileEntityRenderer(NWISTileEntities.VOID_BLOCK.get(), VoidBlockTileEntityRenderer::new);
	}
	
	
}
