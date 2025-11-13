/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.extraorigins.common.power;

import io.github.apace100.apoli.condition.EntityCondition;
import io.github.apace100.apoli.condition.ItemCondition;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.data.TypedDataObjectFactory;
import io.github.apace100.apoli.power.PowerConfiguration;
import io.github.apace100.apoli.power.type.AttributePowerType;
import io.github.apace100.apoli.power.type.PowerType;
import io.github.apace100.apoli.util.AttributedEntityAttributeModifier;
import io.github.apace100.apoli.util.MiscUtil;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import moriyashiine.extraorigins.common.init.ModPowers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ModifyItemAttributePower extends AttributePowerType {
	public static final TypedDataObjectFactory<ModifyItemAttributePower> DATA_FACTORY = createConditionedDataFactory(
			new SerializableData()
					.add("equipment_slot", SerializableDataTypes.EQUIPMENT_SLOT)
					.add("item_condition", ItemCondition.DATA_TYPE.optional(), null)
					.add("tick_rate", SerializableDataTypes.POSITIVE_INT, 20)
					.add("update_health", SerializableDataTypes.BOOLEAN, true)
					.add("modifier", ApoliDataTypes.ATTRIBUTED_ATTRIBUTE_MODIFIER, null)
					.addFunctionedDefault("modifiers", ApoliDataTypes.ATTRIBUTED_ATTRIBUTE_MODIFIERS, data -> MiscUtil.singletonListOrNull(data.get("modifier")))
					.validate(MiscUtil.validateAnyFieldsPresent("modifier", "modifiers")),
			(data, condition) ->
					new ModifyItemAttributePower(
							data.get("equipment_slot"),
							data.get("item_condition"),
							data.getInt("tick_rate"),
							data.get("modifiers"),
							data.getBoolean("update_health"),
							condition
					),
			(powerType, serializableData) ->
					serializableData.instance()
							.set("equipment_slot", powerType.slot)
							.set("item_condition", powerType.itemCondition)
							.set("tick_rate", powerType.tickRate)
							.set("modifiers", powerType.attributedModifiers())
							.set("update_health", powerType.shouldUpdateHealth())
	);

	private final Optional<ItemCondition> itemCondition;
	private final EquipmentSlot slot;
	private final int tickRate;

	public ModifyItemAttributePower(EquipmentSlot slot, Optional<ItemCondition> itemCondition, int tickRate, List<AttributedEntityAttributeModifier> attributedModifiers, boolean updateHealth, Optional<EntityCondition> condition) {
		super(attributedModifiers, updateHealth, condition);
		this.itemCondition = itemCondition;
		this.slot = slot;
		this.tickRate = tickRate;
		setTicking(true);
	}

	@Override
	public void serverTick() {
		if (getHolder().age % tickRate != 0) {
			return;
		}
		for (EquipmentSlot slot : EquipmentSlot.values()) {
			if (this.slot == slot) {
				if (doesApply(getHolder().getEquippedStack(slot))) {
					addTemporaryModifiers(getHolder());
				} else {
					removeModifiers(getHolder());
				}
			}
		}
	}

	public EquipmentSlot getSlot() {
		return slot;
	}

	public boolean doesApply(ItemStack stack) {
		return itemCondition.isEmpty() || itemCondition.get().test(getHolder().getWorld(), stack);
	}

	@Override
	public @NotNull PowerConfiguration<?> getConfig() {
		return ModPowers.MODIFY_ITEM_ATTRIBUTE;
	}
}
