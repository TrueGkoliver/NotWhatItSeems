package com.gkoliver.nwis;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
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
    //𒎒𒊾𒇒𒏅𒅯𒊦𒌅𒅆𒌋𒆟𒌁𒁨𒅜𒊴𒋳𒄝𒋪𒃫𒍠𒇍𒅍𒇬𒁠𒂬𒂅𒇊𒅂𒈉𒍒
    //𒆀𒇊𒈄𒍣𒆚𒄺𒋷𒉴𒆧𒍂𒁘𒂂𒌜𒆻𒂮𒂀𒀆𒂯𒆮𒋶𒂈𒅲𒄌𒅵𒂨𒃟𒊇𒁕𒅭𒃁𒌝𒀛𒀂𒉓𒌱
    // 𒂟𒃾𒄏𒂅𒇆𒌑𒍆𒅀𒏀𒏾𒃺𒆌𒊶𒁹𒈹𒃋𒃎𒊌𒇨𒏧𒁚𒍞𒁷
    // 𒏸𒆋𒌭𒁝𒀂𒀷𒅑𒊋𒄗𒆍𒅡𒍶𒊹𒄥𒍝𒉥𒋁𒈳𒀰𒅢𒂚𒎀𒁳𒈈𒄎𒅵𒀘𒄏𒌵𒊻
    // 𒋱𒎟𒆺𒅺𒎇𒍳𒀶𒋺𒃚𒂕𒈇𒍺𒊧𒎶𒇈𒄥𒂨𒌿𒈂𒌥𒌄𒊔𒁦𒉳𒍟𒉇𒏈𒈞𒎚𒌑𒆤𒌗𒆁𒋺𒌂𒍑𒊄𒎔
    public static final double 𒁢𒁆𒀱𒁻 = 9.0234;


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
