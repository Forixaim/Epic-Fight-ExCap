package net.forixaim.efm_ex;

import com.google.common.collect.Maps;
import com.mna.api.events.SpellCastEvent;
import com.mna.spells.shapes.ShapeBeam;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastType;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.Map;

public class MagicEvents
{
	public static Map<CastType, AnimationProvider<?>> castMap(Map<Object, AnimationProvider<?>> entry)
	{
		Map<CastType, AnimationProvider<?>> castMap = Maps.newHashMap();
		for (Object castType : entry.keySet())
		{
			if (castType instanceof CastType ct)
				castMap.put(ct, entry.get(castType));
		}
		return castMap;
	}

	@SubscribeEvent
	public static void onIronsSpellbookCastEvent(SpellOnCastEvent event)
	{
		AbstractSpell spell = SpellRegistry.getSpell(event.getSpellId());
		if (spell != null && event.getEntity() instanceof ServerPlayer player)
		{
			ServerPlayerPatch playerPatch = EpicFightCapabilities.getEntityPatch(player, ServerPlayerPatch.class);
			if (playerPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability ewc)
			{
				Map<CastType, AnimationProvider<?>> castMap = castMap(ewc.getCastAnimations().get(ewc.getStyle(playerPatch)));
				AnimationProvider<?> aP = castMap.get(spell.getCastType());
				if (aP != null)
				{
					playerPatch.playAnimationSynchronized(aP.get(), 0);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onMNACastEvent(SpellCastEvent event)
	{

	}

}
