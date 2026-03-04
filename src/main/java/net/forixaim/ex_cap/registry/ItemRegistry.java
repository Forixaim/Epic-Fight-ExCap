package net.forixaim.ex_cap.registry;

import net.forixaim.ex_cap.EpicFightEXCapability;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ItemRegistry
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EpicFightEXCapability.MODID);
    public static final Supplier<Item> IRON_LONGSWORD_SHEATH = ITEMS.registerSimpleItem("iron_longsword_sheath", new Item.Properties());
    public static final Supplier<Item> GOLDEN_LONGSWORD_SHEATH = ITEMS.registerSimpleItem("golden_longsword_sheath", new Item.Properties());

}
