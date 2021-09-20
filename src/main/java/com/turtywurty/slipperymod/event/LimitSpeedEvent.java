package com.turtywurty.slipperymod.event;

import java.util.stream.Stream;

import com.turtywurty.slipperymod.core.PacketHandler;
import com.turtywurty.slipperymod.network.packets.LimitMotionPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = "slipperymod", bus = Bus.FORGE)
public class LimitSpeedEvent {

	@SubscribeEvent
	public static void limit(WorldTickEvent event) {
		if (!event.world.isRemote) {
			event.world.getServer().getWorlds().forEach(world -> {
				Stream<Entity> entities = ((ServerWorld) world).getEntities();
				entities.forEach(entity -> {
					if(entity instanceof PlayerEntity) {
						applyDrag(entity.getMotion(), entity);
					}
				});
			});
		}
	}

	private static void applyDrag(Vector3d motion, Entity entity) {
		PacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new LimitMotionPacket(entity.getEntityId()));
	}
}
