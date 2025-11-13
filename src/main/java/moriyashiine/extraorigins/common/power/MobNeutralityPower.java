/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.extraorigins.common.power;

import io.github.apace100.apoli.condition.EntityCondition;
import io.github.apace100.apoli.power.PowerConfiguration;
import io.github.apace100.apoli.power.type.PowerType;
import moriyashiine.extraorigins.common.init.ModPowers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Predicate;

public class MobNeutralityPower extends PowerType {

	public MobNeutralityPower(Optional<EntityCondition> condition) {
		super(condition);
	}

	public boolean shouldBeNeutral(Entity entity) {
		return condition.isPresent() && condition.get().test(entity);
	}

	@Override
	public @NotNull PowerConfiguration<?> getConfig() {
		return ModPowers.MOB_NEUTRALITY;
	}
}
