/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.extraorigins.common.power;


import io.github.apace100.apoli.condition.EntityCondition;
import io.github.apace100.apoli.condition.ItemCondition;
import io.github.apace100.apoli.data.TypedDataObjectFactory;
import io.github.apace100.apoli.power.PowerConfiguration;
import io.github.apace100.apoli.power.type.ModifyFoodPowerType;
import io.github.apace100.apoli.power.type.PowerType;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import moriyashiine.extraorigins.common.init.ModPowers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Predicate;

public class InnateUnbreakingPower extends PowerType {
	public static final TypedDataObjectFactory<InnateUnbreakingPower> DATA_FACTORY = createConditionedDataFactory(
			new SerializableData()
					.add("item_condition",  ItemCondition.DATA_TYPE.optional(), null)
					.add("level", SerializableDataTypes.INT), (data, condition) ->
					new InnateUnbreakingPower(
							data.get("item_condition"),
							data.getInt("level"), condition
					), (powerType, serializableData) ->
					serializableData.instance()
					.set("item_condition", powerType.itemCondition)
					.set("level", powerType.level)
	);

	private final Optional<ItemCondition> itemCondition;
	private final int level;

	public InnateUnbreakingPower(Optional<ItemCondition> itemCondition, int level, Optional<EntityCondition> condition) {
		super(condition);
		this.itemCondition = itemCondition;
		this.level = level;
	}

	public boolean doesApply(ItemStack stack) {
		return itemCondition.isEmpty() || itemCondition.get().test(getHolder().getWorld(), stack);
	}

	public int getLevel() {
		return level;
	}

	@Override
	public @NotNull PowerConfiguration<?> getConfig() {
		return ModPowers.INNATE_UNBREAKING;
	}
}
