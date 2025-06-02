package net.forixaim.efm_ex.capabilities;

import net.forixaim.efm_ex.capabilities.weapon_presets.ExCapWeapons;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum ExCapCategories implements WeaponCategory
{
    BOW,
    CROSSBOW;
    final int id;
    ExCapCategories()
    {
        id = WeaponCategory.ENUM_MANAGER.assign(this);
    }

    @Override
    public int universalOrdinal()
    {
        return id;
    }
}
