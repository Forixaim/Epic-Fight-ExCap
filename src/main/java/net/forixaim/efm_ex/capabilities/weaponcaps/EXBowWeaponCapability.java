package net.forixaim.efm_ex.capabilities.weaponcaps;

import net.minecraft.world.item.UseAnim;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class EXBowWeaponCapability extends EXRangedWeaponCapability
{
	public EXBowWeaponCapability(CapabilityItem.Builder builder) {
		super(builder);
	}

	@Override
	public UseAnim getUseAnimation(LivingEntityPatch<?> playerpatch) {
		return UseAnim.BOW;
	}
}
