package com.turtywurty.slipperymod.core;

import com.turtywurty.slipperymod.network.packets.LimitMotionPacket;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation("slipperymod", "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);

	public static void registerPackets() {
		int id = 0;
		PacketHandler.INSTANCE.registerMessage(id++, LimitMotionPacket.class, LimitMotionPacket::encode,
				LimitMotionPacket::decode, LimitMotionPacket::onRecieved);
	}
}
