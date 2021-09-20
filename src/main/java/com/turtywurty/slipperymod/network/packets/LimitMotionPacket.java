package com.turtywurty.slipperymod.network.packets;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class LimitMotionPacket {

	private int entityToAffect;

	public LimitMotionPacket(int entityToAffectIn) {
		this.entityToAffect = entityToAffectIn;
	}

	public void encode(PacketBuffer packetBuffer) {
		packetBuffer.writeInt(entityToAffect);
	}

	public static LimitMotionPacket decode(PacketBuffer buffer) {
		return new LimitMotionPacket(buffer.readInt());

	}

	@SuppressWarnings("resource")
	public void onRecieved(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			Entity e = Minecraft.getInstance().world.getEntityByID(entityToAffect);
			if(e.getMotion().lengthSquared() > 3.0D && e.getMotion().lengthSquared() < 10.0D) {
				e.setMotion(e.getMotion().scale(0.95D));
			} else if(e.getMotion().lengthSquared() > 10.0D) {
				e.setMotion(e.getMotion().scale(0.9D));
			}
		});
		context.get().setPacketHandled(true);
	}
}
