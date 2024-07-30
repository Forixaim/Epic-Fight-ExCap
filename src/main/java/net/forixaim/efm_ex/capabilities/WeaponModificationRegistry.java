package net.forixaim.efm_ex.capabilities;


import net.forixaim.efm_ex.capabilities.weapon_presets.attacks.*;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.*;

public class WeaponModificationRegistry
{
	private static final BokkenType bokkenType = new BokkenType();
	private static final SwordType swordType = new SwordType();
	private static final SpearType spearType = new SpearType();
	private static final LongswordType longswordType = new LongswordType();
	private static final GreatswordType greatswordType = new GreatswordType();
	private static final DaggerType daggerType = new DaggerType();
	private static final TachiType tachiType = new TachiType();
	private static void InitializeAllWeapons()
	{
		BokkenType.getInstance().init();
		DaggerType.getInstance().init();
		GreatswordType.getInstance().init();
		SwordType.getInstance().init();
		LongswordType.getInstance().init();
		TachiType.getInstance().init();
		SpearType.getInstance().init();
	}

	public static void RegisterWeaponCapabilityModification()
	{
		InitializeAllWeapons();
		BokkenAttacks.injectAttacks();
		SwordAttacks.injectAttacks();
		LongswordAttacks.injectAttacks();
		GreatswordAttacks.injectAttacks();
		SpearAttacks.injectAttacks();
		DaggerAttacks.injectAttacks();
		TachiAttacks.injectAttacks();
	}
}
