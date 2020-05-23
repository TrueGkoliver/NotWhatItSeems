package com.gkoliver.nwis.core.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.Type;

public class NWISConfig {
	public static class Configuration {
		public final BooleanValue DRAGON_EGG;
		public Configuration(ForgeConfigSpec.Builder builderIn) {
			builderIn.comment("NWIS Config")
			.push("general");
			DRAGON_EGG = builderIn.comment("This value determines whether or not Cardboard Dragon eggs can be used. On by default.")
			.translation("notwhatitseems.config.dragon_egg")
			.define("dragon_egg", true);
			builderIn.pop(1);
		}
	}
	public static final ForgeConfigSpec CONFIGSPEC;
	public static final Configuration CONFIG;
	static {
		final Pair<Configuration, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Configuration::new);
		CONFIGSPEC = pair.getRight();
		CONFIG = pair.getLeft();
	}
	
	@SubscribeEvent
	public static void modConfigLoad(ModConfig.Loading event) {
		
	}
	
	@SubscribeEvent
	public static void modConfigChange(ModConfig.Reloading event) {
		
	}
}
