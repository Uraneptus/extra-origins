/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.extraorigins.common.init;

import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.data.ApoliDataTypes;

import io.github.apace100.apoli.power.PowerConfiguration;


import io.github.apace100.apoli.power.type.ModifyFoodPowerType;
import io.github.apace100.apoli.power.type.PowerType;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;

import io.github.apace100.calio.data.SerializableDataTypes;
import moriyashiine.extraorigins.common.ExtraOrigins;
import moriyashiine.extraorigins.common.power.*;
import net.minecraft.registry.Registry;

import java.util.Collections;

public class ModPowers {
	public static final PowerConfiguration<InnateUnbreakingPower> INNATE_UNBREAKING = register(PowerConfiguration.of(ExtraOrigins.id("innate_unbreaking"), InnateUnbreakingPower.DATA_FACTORY));
	public static final PowerConfiguration<MobNeutralityPower> MOB_NEUTRALITY = register(PowerConfiguration.conditionedSimple(ExtraOrigins.id("mob_neutrality"), MobNeutralityPower::new));
	public static final PowerConfiguration<ModifyItemAttributePower> MODIFY_ITEM_ATTRIBUTE = register(PowerConfiguration.of(ExtraOrigins.id("modify_item_attribute"), ModifyItemAttributePower.DATA_FACTORY));

	public static void init() {}

	public static <T extends PowerType> PowerConfiguration<T> register(PowerConfiguration<T> configuration) {

		PowerConfiguration<PowerType> casted = (PowerConfiguration<PowerType>) configuration;
		Registry.register(ApoliRegistries.POWER_TYPE, casted.id(), casted);

		return configuration;

	}
}
