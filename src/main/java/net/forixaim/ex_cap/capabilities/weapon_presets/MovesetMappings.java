package net.forixaim.ex_cap.capabilities.weapon_presets;


import net.forixaim.ex_cap.api.events.ExCapMovesetRegistryEvent;

//EFM Styles
import static net.forixaim.ex_cap.capabilities.weapon_presets.CoreMovesets.*;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.*;


public class MovesetMappings
{
    public static void addMovesets(ExCapMovesetRegistryEvent event)
    {
        event.addMoveset(ExCapWeapons.SWORD.value(), ONE_HAND, sword1HMS);
        event.addMoveset(ExCapWeapons.SWORD.value(), TWO_HAND, sword2HMS);
        event.addMoveset(ExCapWeapons.SWORD.value(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.AXE.value(), ONE_HAND, axeOneHandMS);
        event.addMoveset(ExCapWeapons.AXE.value(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.GLOVE.value(), ONE_HAND, glove);

        event.addMoveset(ExCapWeapons.TACHI.value(), TWO_HAND, tachi2HMS);
        event.addMoveset(ExCapWeapons.TACHI.value(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.LONGSWORD.value(), TWO_HAND, longsword2HMS);
        event.addMoveset(ExCapWeapons.LONGSWORD.value(), ONE_HAND, longsword1HMS);
        event.addMoveset(ExCapWeapons.LONGSWORD.value(), OCHS, LiechtenauerMS);
        event.addMoveset(ExCapWeapons.LONGSWORD.value(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.SPEAR.value(), TWO_HAND, spear2HMS);
        event.addMoveset(ExCapWeapons.SPEAR.value(), ONE_HAND, spear1HMS);
        event.addMoveset(ExCapWeapons.SPEAR.value(), MOUNT, mountedSpearMS);

        event.addMoveset(ExCapWeapons.GREATSWORD.value(), TWO_HAND, greatsword2HMS);

        event.addMoveset(ExCapWeapons.UCHIGATANA.value(), TWO_HAND, UchigatanaBase);
        event.addMoveset(ExCapWeapons.UCHIGATANA.value(), SHEATH, UchigatanaSheathed);
        event.addMoveset(ExCapWeapons.UCHIGATANA.value(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.DAGGER.value(), TWO_HAND, dagger2HMS);
        event.addMoveset(ExCapWeapons.DAGGER.value(), ONE_HAND, dagger1HMS);
        event.addMoveset(ExCapWeapons.DAGGER.value(), MOUNT, mountedSwordMS);

        event.addMoveset(ExCapWeapons.BOW.value(), RANGED, rangedMoveSet);

        event.addMoveset(ExCapWeapons.BOKKEN.value(), ONE_HAND, sword1HMS);
        event.addMoveset(ExCapWeapons.BOKKEN.value(), TWO_HAND, sword2HMS);
        event.addMoveset(ExCapWeapons.BOKKEN.value(), MOUNT, mountedSwordMS);
    }

}
