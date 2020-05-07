package com.gkoliver.nwis.core.util;

import java.util.Collection;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class SharedFunctions {
	public static boolean checkHasNether(ServerPlayerEntity player) {
		Collection<Advancement> advManager = player.getServer().getAdvancementManager().getAllAdvancements();
		for (int i = 0; i<advManager.size(); i++) {
			Advancement[] list = (Advancement[]) advManager.toArray()[i];
			Advancement b = list[i];
			if (b.getId().toString() == "nether/root") {
				if (player.getAdvancements().getProgress(b).isDone()) {
					return true;
				}
			}
			
		}
		return false;
	}

}
