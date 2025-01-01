package net.forixaim.efm_ex.mixin;


import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillDataKeys;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import javax.annotation.Nullable;

@Mixin(value = ParryingSkill.class, remap = false)
public abstract class MixinParryingSkill
{

    @Shadow @Final private static int PARRY_WINDOW;

    @Shadow @Nullable protected abstract StaticAnimation getGuardMotion(PlayerPatch<?> playerpatch, CapabilityItem itemCapability, GuardSkill.BlockType blockType);

    @Inject(method = "getGuardMotion", at = @At(value = "RETURN"))
    private void getGuardMotion(PlayerPatch<?> playerpatch, CapabilityItem itemCapability, GuardSkill.BlockType blockType, CallbackInfoReturnable<StaticAnimation> cir)
    {
        Skill thisSkill = (Skill) (Object) this;
        SkillDataManager dm = playerpatch.getSkill(thisSkill).getDataManager();

        StaticAnimation animation = itemCapability.getGuardMotion((GuardSkill) thisSkill, blockType, playerpatch);

        if (animation != null)
        {
            if (blockType == GuardSkill.BlockType.ADVANCED_GUARD) {
                if (!playerpatch.isLogicalClient())
                {
                    dm.setDataSync(SkillDataKeys.PARRY_MOTION_COUNTER.get(), dm.getDataValue(SkillDataKeys.PARRY_MOTION_COUNTER.get()), (ServerPlayer) playerpatch.getOriginal());
                }
                else
                {
                    dm.setDataSync(SkillDataKeys.PARRY_MOTION_COUNTER.get(), dm.getDataValue(SkillDataKeys.PARRY_MOTION_COUNTER.get()), (LocalPlayer) playerpatch.getOriginal());
                }
            }
        }


    }




}
