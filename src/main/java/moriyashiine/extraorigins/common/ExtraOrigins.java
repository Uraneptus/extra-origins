/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.extraorigins.common;

import moriyashiine.extraorigins.common.init.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ExtraOrigins implements ModInitializer {
	public static final String MOD_ID = "extraorigins";

	@Override
	public void onInitialize() {
		ModSoundEvents.init();
		ModPowers.init();
		ModConditions.init();
		ModScaleTypes.init();
	}

	public static Identifier id(String value) {
		return Identifier.of(MOD_ID, value);
	}
}
