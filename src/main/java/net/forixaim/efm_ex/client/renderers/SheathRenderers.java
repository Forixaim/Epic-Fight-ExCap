package net.forixaim.efm_ex.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.renderer.patched.item.RenderItemBase;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@OnlyIn(Dist.CLIENT)
public class SheathRenderers extends RenderItemBase
{
    @Override
    public void renderItemInHand(ItemStack stack, LivingEntityPatch<?> entitypatch, InteractionHand hand, HumanoidArmature armature, OpenMatrix4f[] poses, MultiBufferSource buffer, PoseStack poseStack, int packedLight, float partialTicks) {
        if (entitypatch.getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability ex && ex.getSheathRenderer(ex.getStyle(entitypatch)).test(entitypatch))
        {
            ItemStack sheath = new ItemStack(ex.getSheath(stack.getItem()));
            OpenMatrix4f modelMatrix = new OpenMatrix4f(this.mainhandcorrectionMatrix);
            modelMatrix.mulFront(poses[armature.toolR.getId()]);

            poseStack.pushPose();
            this.mulPoseStack(poseStack, modelMatrix);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, null, 0);
            poseStack.popPose();

            modelMatrix = new OpenMatrix4f(this.mainhandcorrectionMatrix);
            modelMatrix.mulFront(poses[armature.toolL.getId()]);

            poseStack.pushPose();
            this.mulPoseStack(poseStack, modelMatrix);
            Minecraft.getInstance().getItemRenderer().renderStatic(sheath, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, null, 0);
            poseStack.popPose();
        }
        else
        {
            super.renderItemInHand(stack,  entitypatch, hand,  armature, poses, buffer, poseStack,  packedLight, partialTicks);
        }
    }
}
