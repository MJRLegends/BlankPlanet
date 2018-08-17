package com.mjr.blankplanet.planet;

import com.mjr.blankplanet.BlankPlanet;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.core.event.EventWakePlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlankPlanetEvents {
	@SubscribeEvent
	public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
		if (event.entityLiving.worldObj.provider.getDimensionId() == BlankPlanet.dimensionid) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void GCCoreEventWakePlayer(EventWakePlayer event) {
		if (event.entityLiving.worldObj.provider.getDimensionId() == BlankPlanet.dimensionid) {
			event.entityPlayer.heal(5.0F);

			for (WorldServer worldServer : MinecraftServer.getServer().worldServers) {
				worldServer.setWorldTime(0);
			}
		}
	}
}
