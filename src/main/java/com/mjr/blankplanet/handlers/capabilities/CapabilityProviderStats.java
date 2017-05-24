package com.mjr.blankplanet.handlers.capabilities;

import java.lang.ref.WeakReference;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityProviderStats implements ICapabilitySerializable<NBTTagCompound> {
	private EntityPlayerMP owner;
	private IStatsCapability statsCapability;

	public CapabilityProviderStats(EntityPlayerMP owner) {
		this.owner = owner;
		this.statsCapability = CapabilityStatsHandler.BP_STATS_CAPABILITY.getDefaultInstance();
		this.statsCapability.setPlayer(new WeakReference<>(this.owner));
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityStatsHandler.BP_STATS_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (CapabilityStatsHandler.BP_STATS_CAPABILITY != null && capability == CapabilityStatsHandler.BP_STATS_CAPABILITY) {
            return (T)(statsCapability);
		}

		return null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		statsCapability.saveNBTData(nbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		statsCapability.loadNBTData(nbt);
	}
}
