package com.gkoliver.nwis.common.trigger;

import com.gkoliver.nwis.NotWhatItSeems;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.CuredZombieVillagerTrigger;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class RestrainTrigger extends AbstractCriterionTrigger<RestrainTrigger.RestrainTriggerInstance> {
	public ResourceLocation LOCATION;
	public RestrainTrigger(ResourceLocation location) {
		this.LOCATION = location;
		
	}
	@Override
	public ResourceLocation getId() {
		return LOCATION;
		
	}
	public void trigger(ServerPlayerEntity player) {
	   this.triggerListeners(player, (trigger) -> {
		  return true;
	   });
    }

	/*public RestrainTrigger.RestrainTriggerInstance func_230241_b_(JsonObject json, JsonDeserializationContext context) {
		return new RestrainTriggerInstance(LOCATION);
	}*/
	
	@Override
	protected RestrainTriggerInstance deserializeTrigger(JsonObject p_230241_1_, AndPredicate p_230241_2_,
			ConditionArrayParser p_230241_3_) {
		System.out.println("WE DO BE FIRING THIS DOE");
		return new RestrainTriggerInstance(p_230241_2_, LOCATION);
	}
	
	
	
	public class RestrainTriggerInstance extends CriterionInstance {

		public RestrainTriggerInstance(EntityPredicate.AndPredicate predicate, ResourceLocation criterionIn) {
			super(criterionIn, predicate);
		}

	}

}
