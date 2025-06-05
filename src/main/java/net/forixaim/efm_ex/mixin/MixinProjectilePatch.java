package net.forixaim.efm_ex.mixin;

import net.forixaim.efm_ex.capabilities.weaponcaps.RangedWeapon;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.RangedWeaponCapability;
import yesman.epicfight.world.capabilities.projectile.ProjectilePatch;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.Map;

@Mixin(ProjectilePatch.class)
public abstract class MixinProjectilePatch
{
    @Shadow(remap = false) protected float armorNegation;

    @Shadow(remap = false) protected Vec3 initialFirePosition;

    @Shadow(remap = false) protected float impact;

    @Shadow(remap = false) protected abstract void setMaxStrikes(Projectile t, int i);

    @Inject(method = "onJoinWorld(Lnet/minecraft/world/entity/projectile/Projectile;Lnet/minecraftforge/event/entity/EntityJoinLevelEvent;)V", at = @At("RETURN"), remap = false)
    public void onJoinWorld(Projectile projectileEntity, EntityJoinLevelEvent event, CallbackInfo ci)
    {
        Entity shooter = projectileEntity.getOwner();
        boolean flag = true;

        if (shooter instanceof LivingEntity livingshooter) {
            this.initialFirePosition = shooter.position();
            ItemStack heldItem = livingshooter.getMainHandItem();
            CapabilityItem itemCap = EpicFightCapabilities.getItemStackCapability(heldItem);

            if (itemCap instanceof RangedWeapon) {
                Map<Attribute, AttributeModifier> modifierMap = itemCap.getDamageAttributesInCondition(CapabilityItem.Styles.RANGED);

                if (modifierMap != null) {
                    this.armorNegation =
                            modifierMap.containsKey(EpicFightAttributes.ARMOR_NEGATION.get()) ?
                                    (float)modifierMap.get(EpicFightAttributes.ARMOR_NEGATION.get()).getAmount()
                                    : (float)EpicFightAttributes.ARMOR_NEGATION.get().getDefaultValue();

                    this.impact =
                            modifierMap.containsKey(EpicFightAttributes.IMPACT.get()) ?
                                    (float)modifierMap.get(EpicFightAttributes.IMPACT.get()).getAmount()
                                    : (float)EpicFightAttributes.IMPACT.get().getDefaultValue();

                    if (modifierMap.containsKey(EpicFightAttributes.MAX_STRIKES.get())) {
                        this.setMaxStrikes(projectileEntity, (int)modifierMap.get(EpicFightAttributes.MAX_STRIKES.get()).getAmount());
                    }
                }

                flag = false;
            }
        }

        if (flag) {
            this.armorNegation = 0.0F;
            this.impact = 0.0F;
        }
    }
}
