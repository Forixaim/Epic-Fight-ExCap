package net.forixaim.efm_ex.capabilities;


import net.forixaim.efm_ex.capabilities.weapon_presets.attacks.*;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.*;

public class WeaponModificationRegistry
{

	public static void RegisterWeaponCapabilityModification()
	{
		BokkenAttacks.injectAttacks();
		SwordAttacks.injectAttacks();
		LongswordAttacks.injectAttacks();
		GreatswordAttacks.injectAttacks();
		SpearAttacks.injectAttacks();
		DaggerAttacks.injectAttacks();
		TachiAttacks.injectAttacks();
	}
}
