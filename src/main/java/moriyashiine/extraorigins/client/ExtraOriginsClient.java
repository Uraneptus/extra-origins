/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.extraorigins.client;

import moriyashiine.extraorigins.client.event.DelayedHitboxEvent;
import moriyashiine.extraorigins.client.packet.MountS2CPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ExtraOriginsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		initPackets();
		ClientTickEvents.END_WORLD_TICK.register(new DelayedHitboxEvent());
	}

	private void initPackets() {
		ClientPlayNetworking.registerGlobalReceiver(MountS2CPacket.ID, new MountS2CPacket.Receiver());
	}
}
