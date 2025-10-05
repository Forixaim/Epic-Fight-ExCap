package net.forixaim.ex_cap.capabilities.weapon_presets;


import net.forixaim.ex_cap.api.events.ExCapMovesetRegistryEvent;

//EFM Styles
import static net.forixaim.ex_cap.capabilities.weapon_presets.CoreMovesets.*;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.*;


public class MovesetMappings
{
    public static void addMovesets(ExCapMovesetRegistryEvent event)
    {
        event.addMoveset(ExCapWeapons.SWORD.get(), ONE_HAND, sword1HMS);
        event.addMoveset(ExCapWeapons.SWORD.get(), TWO_HAND, sword2HMS);
        event.addMoveset(ExCapWeapons.SWORD.get(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.AXE.get(), ONE_HAND, axeOneHandMS);
        event.addMoveset(ExCapWeapons.AXE.get(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.GLOVE.get(), ONE_HAND, glove);

        event.addMoveset(ExCapWeapons.TACHI.get(), TWO_HAND, tachi2HMS);
        event.addMoveset(ExCapWeapons.TACHI.get(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.LONGSWORD.get(), TWO_HAND, longsword2HMS);
        event.addMoveset(ExCapWeapons.LONGSWORD.get(), ONE_HAND, longsword1HMS);
        event.addMoveset(ExCapWeapons.LONGSWORD.get(), OCHS, LiechtenauerMS);
        event.addMoveset(ExCapWeapons.LONGSWORD.get(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.SPEAR.get(), TWO_HAND, spear2HMS);
        event.addMoveset(ExCapWeapons.SPEAR.get(), ONE_HAND, spear1HMS);
        event.addMoveset(ExCapWeapons.SPEAR.get(), MOUNT, mountedSpearMS);

        event.addMoveset(ExCapWeapons.GREATSWORD.get(), TWO_HAND, greatsword2HMS);

        event.addMoveset(ExCapWeapons.UCHIGATANA.get(), TWO_HAND, UchigatanaBase);
        event.addMoveset(ExCapWeapons.UCHIGATANA.get(), SHEATH, UchigatanaSheathed);
        event.addMoveset(ExCapWeapons.UCHIGATANA.get(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.DAGGER.get(), TWO_HAND, dagger2HMS);
        event.addMoveset(ExCapWeapons.DAGGER.get(), ONE_HAND, dagger1HMS);
        event.addMoveset(ExCapWeapons.DAGGER.get(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.BOW.get(), RANGED, rangedMoveSet);

        event.addMoveset(ExCapWeapons.BOKKEN.get(), ONE_HAND, sword1HMS);
        event.addMoveset(ExCapWeapons.BOKKEN.get(), TWO_HAND, sword2HMS);
        event.addMoveset(ExCapWeapons.BOKKEN.get(), MOUNT, mountedSwordMS);
    }

}
