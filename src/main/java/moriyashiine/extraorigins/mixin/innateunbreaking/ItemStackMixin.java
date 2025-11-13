/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.extraorigins.mixin.innateunbreaking;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.apace100.apoli.component.PowerHolderComponent;
import moriyashiine.extraorigins.common.power.InnateUnbreakingPower;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@WrapOperation(method = "damage(ILnet/minecraft/server/world/ServerWorld;Lnet/minecraft/server/network/ServerPlayerEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getItemDamage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/item/ItemStack;I)I"))
	private int extraorigins$innateUnbreaking(ServerWorld world, ItemStack stack, int baseItemDamage, Operation<Integer> original, int a, ServerWorld w, @Nullable ServerPlayerEntity player, Consumer<Item> c) {
		int level = original.call(world, stack, baseItemDamage);
		if (player != null) {
			for (InnateUnbreakingPower power : PowerHolderComponent.getPowerTypes(player, InnateUnbreakingPower.class)) {
				if (power.doesApply((ItemStack) (Object) this) && power.isActive()) {
					level -= power.getLevel();
				}
			}
		}
		return level;
	}
}
