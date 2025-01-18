package net.forixaim.efm_ex.mixin;

import com.google.gson.JsonElement;
import net.forixaim.efm_ex.api.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.AnimationManager;

import java.util.Map;

@Mixin(value = AnimationManager.class, remap = false)
public class MixinAnimationManager
{
    @Inject(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Ljava/util/Map;", at = @At("RETURN"))
    protected void prep(ResourceManager resourceManager, ProfilerFiller profilerIn, CallbackInfoReturnable<Map<ResourceLocation, JsonElement>> cir)
    {
        Registries.registerMovesets();
        Registries.registerCapabilities();
    }
}
