package net.forixaim.efm_ex.api.animation.events;

import com.mna.api.ManaAndArtificeMod;
import com.mna.api.spells.collections.Shapes;
import com.mna.items.sorcery.ItemSpell;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.Objects;

public class CastMNASpell
{
	public static final AnimationEvent.AnimationEventConsumer castSpell = ((livingEntityPatch, staticAnimation, objects) -> {
		if (ModList.get().isLoaded(ManaAndArtificeMod.ID))
		{
			if (livingEntityPatch instanceof ServerPlayerPatch playerPatch && livingEntityPatch.getOriginal().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ItemSpell spell)
			{
				if (Objects.requireNonNull(spell.getSpell(playerPatch.getOriginal().getItemInHand(InteractionHand.MAIN_HAND), playerPatch.getOriginal()).getShape()).getPart() == Shapes.PROJECTILE)
				{
					ItemSpell.castSpellOnUse(livingEntityPatch.getOriginal().getItemInHand(InteractionHand.MAIN_HAND),
							livingEntityPatch.getOriginal().level(),
							playerPatch.getOriginal(), InteractionHand.MAIN_HAND, itemStack -> true);
				}
			}
		}
	});
}
