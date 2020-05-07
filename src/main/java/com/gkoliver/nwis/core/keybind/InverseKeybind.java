package com.gkoliver.nwis.core.keybind;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class InverseKeybind {
	public static final KeyBinding KEYBIND_INVERSE = new KeyBinding("key.notwhatitseems.inverse", KeyConflictContext.IN_GAME, InputMappings.getInputByName("key.keyboard.i"), "key.categories.gameplay");
	public static final KeyBinding KEYBIND_BOTHSIDES = new KeyBinding("key.notwhatitseems.bothsides", KeyConflictContext.IN_GAME, InputMappings.getInputByName("key.keyboard.o"), "key.categories.gameplay");
	public static void addKey() {
		ClientRegistry.registerKeyBinding(KEYBIND_INVERSE);
		ClientRegistry.registerKeyBinding(KEYBIND_BOTHSIDES);
	}
}
