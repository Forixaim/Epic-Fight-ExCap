package net.forixaim.ex_cap.mixin;

import org.spongepowered.asm.mixin.Mixin;
import yesman.epicfight.client.renderer.patched.entity.PPlayerRenderer;

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
