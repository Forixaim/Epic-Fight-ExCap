package net.forixaim.efm_ex.api.animation.events;

import com.google.common.collect.Lists;
import com.mna.api.ManaAndArtificeMod;
import com.mna.api.spells.base.ISpellDefinition;
import com.mna.api.spells.collections.Shapes;
import com.mna.api.spells.parts.Shape;
import com.mna.items.sorcery.ItemSpell;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.CastType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import com.mna.api.sound.SFX.Spell.Cast;

import java.util.List;

public class CastSpell
{
	public static final AnimationEvent.AnimationEventConsumer castMNASpell = ((livingEntityPatch, staticAnimation, objects) -> {
		if (ModList.get().isLoaded(ManaAndArtificeMod.ID))
		{
			final List<Shape> ALLOWED_SHAPES = Lists.newArrayList(
					Shapes.BOLT,
					Shapes.TOUCH,
					Shapes.PROJECTILE
			);
			ItemStack item = livingEntityPatch.getOriginal().getItemInHand(InteractionHand.MAIN_HAND);
			if (livingEntityPatch instanceof ServerPlayerPatch playerPatch && livingEntityPatch.getOriginal().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ItemSpell spell)
			{
				ISpellDefinition spellDefinition = spell.getSpell(item, playerPatch.getOriginal());
				if (spellDefinition.getShape() != null && ALLOWED_SHAPES.contains(spellDefinition.getShape().getPart()))
				{
					ItemSpell.castSpellOnUse(livingEntityPatch.getOriginal().getItemInHand(InteractionHand.MAIN_HAND),
							livingEntityPatch.getOriginal().level(),
							playerPatch.getOriginal(), InteractionHand.MAIN_HAND, itemStack -> true);
					playerPatch.playSound(Cast.ARCANE, 0, 0);

				}
			}
		}
	});

	public static final AnimationEvent.AnimationEventConsumer castIronsSpell = ((livingEntityPatch, staticAnimation, objects) -> {
		if (ModList.get().isLoaded(IronsSpellbooks.MODID))
		{
			if (livingEntityPatch instanceof ServerPlayerPatch playerPatch)
			{
				if (MagicData.getPlayerMagicData(playerPatch.getOriginal()).getCastingSpell().getSpell().getCastType() == CastType.INSTANT);
				{

				}
			}

		}
	});
}
