package net.forixaim.efm_ex.capabilities.weaponcaps;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.UseAnim;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class EXBowWeaponCapability extends EXRangedWeaponCapability
{
	public EXBowWeaponCapability(CapabilityItem.Builder builder) {
		super(builder);
	}

	public LivingMotion getLivingMotion(LivingEntityPatch<?> entitypatch, InteractionHand hand) {
		return entitypatch.getOriginal().isUsingItem() && entitypatch.getOriginal().getUseItem().getUseAnimation() == UseAnim.BOW ? LivingMotions.AIM : null;
	}

	@Override
	public UseAnim getUseAnimation(LivingEntityPatch<?> playerpatch) {
		return UseAnim.BOW;
	}
}
