package net.forixaim.ex_cap.capabilities;

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
