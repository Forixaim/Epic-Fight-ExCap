package net.forixaim.ex_cap.capabilities.weaponcaps;

import net.minecraft.world.InteractionHand;
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



	public LivingMotion getLivingMotion(LivingEntityPatch<?> entityPatch, InteractionHand hand) {
		return entityPatch.getOriginal().isUsingItem() && entityPatch.getOriginal().getUseItem().getUseAnimation() == UseAnim.BOW ? LivingMotions.AIM : null;
	}

    @Override
	public UseAnim getUseAnimation(LivingEntityPatch<?> playerpatch) {
		return UseAnim.BOW;
	}
}
