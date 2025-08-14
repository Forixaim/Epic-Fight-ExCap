package net.forixaim.ex_cap.mixin;

import net.forixaim.ex_cap.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.events.engine.ControlEngine;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.entity.eventlistener.MovementInputEvent;

@Mixin(value = GuardSkill.class, remap = false)
public class MixinGuard
{
	@Unique
	private GuardSkill epicFight_EXCapability$self = (GuardSkill) (Object) this;

	@Inject(method = "lambda$onInitiate$15", at = @At("HEAD"), remap = false)
	private void onInitiate(SkillContainer container, MovementInputEvent event, CallbackInfo ci)
	{
		if (container.getExecutor().getOriginal().isUsingItem() && container.getExecutor().getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability weaponCapability)
		{
			if (weaponCapability.getGuardMotion(epicFight_EXCapability$self, GuardSkill.BlockType.GUARD, event.getPlayerPatch()) != null)
			{
				LocalPlayer clientPlayer = event.getPlayerPatch().getOriginal();
				clientPlayer.setSprinting(false);
				clientPlayer.sprintTriggerTime = -1;
				Minecraft mc = Minecraft.getInstance();
				ControlEngine.setKeyBind(mc.options.keySprint, false);
			}
		}
	}
}
