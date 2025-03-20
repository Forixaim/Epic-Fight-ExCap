package net.forixaim.efm_ex;

import com.google.common.collect.Maps;
import com.mna.api.events.SpellCastEvent;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastType;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.compat.EXSpellCapability;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.Map;

public class MagicEvents
{

	private static Map<CastType, AnimationManager.AnimationAccessor<? extends StaticAnimation>> castMap(Map<Object, AnimationManager.AnimationAccessor<? extends StaticAnimation>> entry)
	{
		if (ModList.get().isLoaded(IronsSpellbooks.MODID))
		{
			Map<CastType, AnimationManager.AnimationAccessor<? extends StaticAnimation>> castMap = Maps.newHashMap();
			for (Object castType : entry.keySet())
			{
				if (castType instanceof CastType ct)
					castMap.put(ct, entry.get(castType));
			}
			return castMap;
		}
		return null;
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
				Map<CastType, AnimationManager.AnimationAccessor<? extends StaticAnimation>> castMap = castMap(ewc.getCastAnimations().get(ewc.getStyle(playerPatch)));
				AnimationManager.AnimationAccessor<? extends StaticAnimation> aP = castMap.get(spell.getCastType());
				if (aP != null)
				{
					playerPatch.playAnimationSynchronized(aP, 0);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onMNACastEvent(SpellCastEvent event)
	{
		LivingEntityPatch<?> entityPatch = EpicFightCapabilities.getEntityPatch(event.getSource().getCaster(), LivingEntityPatch.class);
		if (entityPatch instanceof ServerPlayerPatch playerPatch)
		{
			if (playerPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXSpellCapability eswc)
			{

			}
		}

	}

}
