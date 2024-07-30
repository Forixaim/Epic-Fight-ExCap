package net.forixaim.efm_ex.capabilities.weapon_styles;

import yesman.epicfight.world.capabilities.item.Style;

public enum ExampleStyle implements Style
{
	HEAVY(false);
	private int id;
	private boolean offHandUse;
	ExampleStyle(boolean offHandUse)
	{
		this.id = Style.ENUM_MANAGER.assign(this);
	}
	@Override
	public boolean canUseOffhand()
	{
		return false;
	}

	@Override
	public int universalOrdinal()
	{
		return Style.ENUM_MANAGER.assign(this);
	}
}
