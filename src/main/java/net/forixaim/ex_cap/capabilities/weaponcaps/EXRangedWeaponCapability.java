package net.forixaim.ex_cap.capabilities.weaponcaps;

import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

public class EXRangedWeaponCapability extends EXWeaponCapability implements RangedWeapon
{

	public EXRangedWeaponCapability(WeaponCapability.Builder builder) {
		super(builder);
		EXRangedWeaponCapability.Builder rangedBuilder = (EXRangedWeaponCapability.Builder)builder;
	}

	public boolean availableOnHorse() {
		return true;
	}

	public boolean canBePlacedOffhand() {
		return false;
	}

	public static EXRangedWeaponCapability.Builder builder() {
		return new EXRangedWeaponCapability.Builder();
	}

	public CapabilityItem.ZoomInType getZoomInType() {
		return this.zoomInType;
	}

}
