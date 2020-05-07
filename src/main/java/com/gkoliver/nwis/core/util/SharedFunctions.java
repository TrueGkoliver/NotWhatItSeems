package com.gkoliver.nwis.core.util;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class SharedFunctions {
	public static boolean checkHasNether(ServerPlayerEntity player) {
		Collection<Advancement> advManager = player.getServer().getAdvancementManager().getAllAdvancements();
		Iterator<Advancement> iter = advManager.iterator();
		while (iter.hasNext()) {
			Advancement zoom = iter.next();
			if (zoom.getId().toString().contains("nether/root")) {
				if (player.getAdvancements().getProgress(zoom).isDone()) {
					return true;
				}
			}
			
		}
		return false;
	}

}
