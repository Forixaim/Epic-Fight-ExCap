package net.forixaim.efm_ex.mixin;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.client.model.Meshes;
import yesman.epicfight.client.mesh.HumanoidMesh;
import yesman.epicfight.client.renderer.patched.entity.PPlayerRenderer;
import yesman.epicfight.client.world.capabilites.entitypatch.player.AbstractClientPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

@Mixin(value = PPlayerRenderer.class, remap = false)
public class MixinPPlayerRenderer
{

//    @Inject(method = "getMeshProvider(Lyesman/epicfight/client/world/capabilites/entitypatch/player/AbstractClientPlayerPatch;)Lyesman/epicfight/api/asset/AssetAccessor;", at = @At("RETURN"), remap = false, cancellable = true)
//    public void getMeshProvider(AbstractClientPlayerPatch<AbstractClientPlayer> entityPatch, CallbackInfoReturnable<AssetAccessor<HumanoidMesh>> cir)
//    {
//        if (entityPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.AXE)
//        {
//            cir.setReturnValue(Meshes.BIPED);
//        }
//    }
}
