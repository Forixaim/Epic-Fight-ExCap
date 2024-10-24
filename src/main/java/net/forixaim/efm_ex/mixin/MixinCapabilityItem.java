package net.forixaim.efm_ex.mixin;

import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPChangeSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

@Mixin(CapabilityItem.class)
public abstract class MixinCapabilityItem
{
	@Unique public CapabilityItem epicFight_EXCapability$inst = (CapabilityItem) (Object) this;

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
}
