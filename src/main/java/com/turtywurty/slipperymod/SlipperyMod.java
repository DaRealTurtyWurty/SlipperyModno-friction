package com.turtywurty.slipperymod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.turtywurty.slipperymod.core.PacketHandler;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("slipperymod")
public class SlipperyMod {
	public static final Logger LOGGER = LogManager.getLogger();

	public SlipperyMod() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		for (Block block : ForgeRegistries.BLOCKS) {
			if (block.getRegistryName().getNamespace().equals("minecraft")) {
				block.slipperiness = 1.0989010989010989010989010989011f;
			}
		}
		PacketHandler.registerPackets();
	}

	public static float getMultiplier() {
		return 0.91f;
	}
}
