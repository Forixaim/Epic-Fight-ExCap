package net.forixaim.efm_ex.mixin;

import net.forixaim.bs_api.BattleArtsAPI;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = PlayerPatch.class, remap = false)
public abstract class MixinPlayerPatch
{
	@Unique
	private PlayerPatch<?> inst = (PlayerPatch<?>) (Object) this;

	//TODO: Implement a switch mode animation;
	@Inject(method = "toggleMode", at = @At("RETURN"), remap = false)
	public void toggleMode(CallbackInfo ci)
	{
		if (inst.isBattleMode())
		{
			if (ModList.get().isLoaded(BattleArtsAPI.MOD_ID))
			{

			}
		}
	}

}
