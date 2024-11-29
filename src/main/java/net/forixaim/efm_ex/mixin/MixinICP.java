package net.forixaim.efm_ex.mixin;

import com.mna.api.ManaAndArtificeMod;
import com.mna.items.sorcery.ItemSpell;
import net.forixaim.efm_ex.capabilities.CapabilityRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.provider.ItemCapabilityProvider;

import java.util.Map;
import java.util.function.Function;

@Mixin(value = ItemCapabilityProvider.class)
public abstract class MixinICP
{
	@Shadow @Final private static Map<Class<? extends Item>, Function<Item, CapabilityItem.Builder>> CAPABILITY_BY_CLASS;

	@Inject(method = "registerWeaponTypesByClass", at = @At("RETURN"))
	private static void injectWeapons(CallbackInfo ci)
	{
		if (ModList.get().isLoaded(ManaAndArtificeMod.ID))
		{
			CAPABILITY_BY_CLASS.put(ItemSpell.class, CapabilityRegistry.SPELL);
		}

	}
}
