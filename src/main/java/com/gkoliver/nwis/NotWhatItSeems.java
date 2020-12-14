package com.gkoliver.nwis;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gkoliver.nwis.client.render.VoidBlockTileEntityRenderer;
import com.gkoliver.nwis.common.gui.ImposterContainer;
import com.gkoliver.nwis.common.trigger.RestrainTrigger;
import com.gkoliver.nwis.core.config.NWISConfig;
import com.gkoliver.nwis.core.register.BlockRegistry;
import com.gkoliver.nwis.core.register.ItemRegistry;
import com.gkoliver.nwis.core.register.TileEntityRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NotWhatItSeems.MODID)
@Mod.EventBusSubscriber(modid=NotWhatItSeems.MODID)
public class NotWhatItSeems
{
	//Common mods loaded
	public static boolean endergetic = false;
	public static boolean quark = false;
	public static boolean ua = false;
	public static boolean atmospheric = false;
	public static boolean atumwhatever = false;
	public static boolean environmental = false;
	public static boolean buzzierbees = true;
	public static final String MODID = "notwhatitseems";
    private static final Logger LOGGER = LogManager.getLogger();

    public NotWhatItSeems() {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.BLOCKS.register(eventBus);
        ItemRegistry.ITEMS.register(eventBus);
        TileEntityRegistry.TILE_ENTITIES.register(eventBus);
        TileEntityRegistry.CONTAINERS.register(eventBus);
        if (ModList.get().isLoaded("quark")) {
        	quark = true;
        }
        if (ModList.get().isLoaded("endergetic")) {
        	endergetic = true;
        }
        if (ModList.get().isLoaded("upgrade_aquatic")) {
        	ua = true;
        }
        if (ModList.get().isLoaded("atmospheric")) {
        	atmospheric = true;
        }
        if (ModList.get().isLoaded("autumnity")) {
        	atumwhatever = true;
        }
        if (ModList.get().isLoaded("environmental")) {
        	environmental = true;
        }
        if (ModList.get().isLoaded("buzzierbees")) {
        	buzzierbees = true;
        }
        Triggers.RESTRAIN_VOID = Triggers.getConstrain();
        Triggers.CROP_CHANGES = Triggers.getCropChange();
        ModLoadingContext.get().registerConfig(Type.COMMON, NWISConfig.CONFIGSPEC, "nwis-common.toml");
    }
    
    public static class Triggers {
    	public static RestrainTrigger RESTRAIN_VOID;
    	public static RestrainTrigger CROP_CHANGES;
    	public static RestrainTrigger getCropChange() {
    		return CriteriaTriggers.register(new RestrainTrigger(new ResourceLocation(NotWhatItSeems.MODID, "crop_change")));
    	}
    	public static RestrainTrigger getConstrain() {
    		return CriteriaTriggers.register(new RestrainTrigger(new ResourceLocation(NotWhatItSeems.MODID, "restrain_void")));
    	}
    }

}
