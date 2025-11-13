/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.extraorigins.common.init;

import io.github.apace100.apoli.condition.ConditionConfiguration;
import io.github.apace100.apoli.condition.type.DamageConditionType;
import io.github.apace100.apoli.condition.type.EntityConditionType;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import moriyashiine.extraorigins.common.ExtraOrigins;
import moriyashiine.extraorigins.common.conditions.PiglinSafeCondition;
import moriyashiine.extraorigins.common.conditions.ShotFromCrossbowCondition;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;

public class ModConditions {

 	public static final ConditionConfiguration<DamageConditionType> SHOT_FROM_CROSSBOW = ConditionConfiguration.simple(ExtraOrigins.id("shot_from_crossbow"), ShotFromCrossbowCondition::new);
 	public static final ConditionConfiguration<EntityConditionType> PIGLIN_SAFE = ConditionConfiguration.simple(ExtraOrigins.id("piglin_safe"), PiglinSafeCondition::new);

	public static void init() {
		Registry.register(ApoliRegistries.DAMAGE_CONDITION_TYPE, SHOT_FROM_CROSSBOW.id(), SHOT_FROM_CROSSBOW);
		Registry.register(ApoliRegistries.ENTITY_CONDITION_TYPE, PIGLIN_SAFE.id(), PIGLIN_SAFE);
	}
}
