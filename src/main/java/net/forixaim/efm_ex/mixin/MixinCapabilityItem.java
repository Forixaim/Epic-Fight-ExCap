package net.forixaim.efm_ex.mixin;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.FistType;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPChangeSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.List;

@Mixin(CapabilityItem.class)
public abstract class MixinCapabilityItem
{
	@Shadow public abstract Style getStyle(LivingEntityPatch<?> entitypatch);

	@Unique public CapabilityItem epicFight_EXCapability$inst = (CapabilityItem) (Object) this;

	@Unique private FistType fistType = FistType.getInstance();

	@Inject(method = "changeWeaponInnateSkill", at = @At("RETURN"), remap = false)
	public void changeInnate(PlayerPatch<?> playerpatch, ItemStack itemstack, CallbackInfo ci)
	{
		if (epicFight_EXCapability$inst instanceof EXWeaponCapability exWeaponCapability)
		{
			Skill skill = exWeaponCapability.getPassiveProvider().apply(playerpatch);
			SkillContainer passiveSkillContainer = playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE);

			if (skill != null) {
				if (passiveSkillContainer.getSkill() != skill) {
					passiveSkillContainer.setSkill(skill);
					EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.WEAPON_PASSIVE, skill.toString(), SPChangeSkill.State.ENABLE), (ServerPlayer)playerpatch.getOriginal());
				}
			} else {
				passiveSkillContainer.setSkill(null);
				EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.WEAPON_PASSIVE, "empty", SPChangeSkill.State.ENABLE), (ServerPlayer)playerpatch.getOriginal());
			}
		}
	}

	@Inject(method = "getStyle", at = @At("HEAD"), remap = false, cancellable = true)
	public void getStyle(LivingEntityPatch<?> entityPatch, CallbackInfoReturnable<Style> cir)
	{
		if (entityPatch instanceof PlayerPatch)
		{
			cir.setReturnValue(fistType.export().build().getStyle(entityPatch));
		}
	}

	@Inject(method = "getAutoAttckMotion", at = @At("HEAD"), remap = false, cancellable = true)
	public void getAutoAttackMotion(PlayerPatch<?> patch, CallbackInfoReturnable<List<AnimationProvider<?>>> cir)
	{
		cir.setReturnValue(fistType.mapSets().get(getStyle(patch)).exportAttackMotions());
	}


}
