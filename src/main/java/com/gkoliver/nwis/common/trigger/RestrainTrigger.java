package com.gkoliver.nwis.common.trigger;

import com.gkoliver.nwis.NotWhatItSeems;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class RestrainTrigger extends AbstractCriterionTrigger<RestrainTrigger.RestrainTriggerInstance> {
	public static final ResourceLocation LOCATION = new ResourceLocation(NotWhatItSeems.MODID, "restrain_void");
	@Override
	public ResourceLocation getId() {
		return LOCATION;
	}
	public void trigger(ServerPlayerEntity player) {
	      this.func_227070_a_(player.getAdvancements(), (trigger) -> {
	         return true;
	      });
	   }
	@Override
	public RestrainTrigger.RestrainTriggerInstance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		return new RestrainTriggerInstance(LOCATION);
	}
	
	public class RestrainTriggerInstance extends CriterionInstance {

		public RestrainTriggerInstance(ResourceLocation criterionIn) {
			super(criterionIn);
		}

	}

}
