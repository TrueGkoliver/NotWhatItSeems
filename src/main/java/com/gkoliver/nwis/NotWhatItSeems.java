package com.gkoliver.nwis;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gkoliver.nwis.client.render.VoidBlockTileEntityRenderer;
import com.gkoliver.nwis.core.register.BlockRegistry;
import com.gkoliver.nwis.core.register.ItemRegistry;
import com.gkoliver.nwis.core.register.TileEntityRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NotWhatItSeems.MODID)
@Mod.EventBusSubscriber(modid=NotWhatItSeems.MODID)
public class NotWhatItSeems
{
	public static final String MODID = "notwhatitseems";
    private static final Logger LOGGER = LogManager.getLogger();

    public NotWhatItSeems() {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        BlockRegistry.BLOCKS.register(eventBus);
        ItemRegistry.ITEMS.register(eventBus);
        TileEntityRegistry.TILE_ENTITIES.register(eventBus);
        TileEntityRegistry.CONTAINERS.register(eventBus);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }
}
