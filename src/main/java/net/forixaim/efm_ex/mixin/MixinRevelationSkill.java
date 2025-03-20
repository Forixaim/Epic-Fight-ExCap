package net.forixaim.efm_ex.mixin;

import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.identity.RevelationSkill;

@Mixin(RevelationSkill.class)
public class MixinRevelationSkill
{
    @Inject(method = "executeOnServer", at = @At(value = "INVOKE", target = "Lyesman/epicfight/skill/Skill;executeOnServer(Lyesman/epicfight/skill/SkillContainer;Lnet/minecraft/network/FriendlyByteBuf;)V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args, CallbackInfo ci)
    {
        if (container.getServerExecutor().getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability eXWeapon)
        {
            AnimationManager.AnimationAccessor<? extends AttackAnimation> attack = eXWeapon.getRevelationAnimation(container.getServerExecutor());
            if (attack != null)
            {
                container.getServerExecutor().playAnimationSynchronized(attack, 0);
                ci.cancel();
            }
        }
    }
}
