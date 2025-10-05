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

        event.addMoveset(ExCapWeapons.AXE, ONE_HAND, axeOneHandMS);
        event.addMoveset(ExCapWeapons.AXE, MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.GLOVE, ONE_HAND, glove);

        event.addMoveset(ExCapWeapons.TACHI, TWO_HAND, tachi2HMS);
        event.addMoveset(ExCapWeapons.TACHI, MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.LONGSWORD, TWO_HAND, longsword2HMS);
        event.addMoveset(ExCapWeapons.LONGSWORD, ONE_HAND, longsword1HMS);
        event.addMoveset(ExCapWeapons.LONGSWORD, OCHS, LiechtenauerMS);
        event.addMoveset(ExCapWeapons.LONGSWORD, MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.SPEAR, TWO_HAND, spear2HMS);
        event.addMoveset(ExCapWeapons.SPEAR, ONE_HAND, spear1HMS);
        event.addMoveset(ExCapWeapons.SPEAR, MOUNT, mountedSpearMS);

        event.addMoveset(ExCapWeapons.GREATSWORD, TWO_HAND, greatsword2HMS);

        event.addMoveset(ExCapWeapons.UCHIGATANA, TWO_HAND, UchigatanaBase);
        event.addMoveset(ExCapWeapons.UCHIGATANA, SHEATH, UchigatanaSheathed);
        event.addMoveset(ExCapWeapons.UCHIGATANA, MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.DAGGER, TWO_HAND, dagger2HMS);
        event.addMoveset(ExCapWeapons.DAGGER, ONE_HAND, dagger1HMS);
        event.addMoveset(ExCapWeapons.DAGGER, MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.BOW, RANGED, rangedMoveSet);

        event.addMoveset(ExCapWeapons.BOKKEN.get(), ONE_HAND, sword1HMS);
        event.addMoveset(ExCapWeapons.BOKKEN.get(), TWO_HAND, sword2HMS);
        event.addMoveset(ExCapWeapons.BOKKEN.get(), MOUNT, mountedSwordMS);
    }

}
