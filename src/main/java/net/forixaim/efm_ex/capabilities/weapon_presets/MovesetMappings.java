package net.forixaim.efm_ex.capabilities.weapon_presets;

import com.mna.api.ManaAndArtificeMod;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.Map;

//EFM Styles
import static net.forixaim.efm_ex.capabilities.weapon_presets.CoreMovesets.*;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.COMMON;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.ONE_HAND;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.TWO_HAND;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.MOUNT;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.SHEATH;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.OCHS;


public class MovesetMappings
{
    public static Map<Style, MoveSet> bokkenMovesets = Map.ofEntries(
            Map.entry(ONE_HAND, longsword1HMS),
            Map.entry(TWO_HAND, sword2HMS),
            Map.entry(MOUNT, mountedSwordMS)
    );

    public static Map<Style, MoveSet> swordMovesets = Map.ofEntries(
            Map.entry(ONE_HAND, sword1HMS),
            Map.entry(TWO_HAND, sword2HMS),
            Map.entry(MOUNT, mountedSwordMS)
    );

    public static Map<Style, MoveSet> axeMovesets = Map.ofEntries(
            Map.entry(Styles.ONE_HAND, axeOneHandMS)
    );

    public static Map<Style, MoveSet> daggerMovesets = Map.ofEntries(
            Map.entry(ONE_HAND, dagger1HMS),
            Map.entry(TWO_HAND, dagger2HMS),
            Map.entry(MOUNT, mountedSwordMS)
    );

    public static Map<Style, MoveSet> uchigatanaMovesets = Map.ofEntries(
            Map.entry(TWO_HAND, UchigatanaBase),
            Map.entry(SHEATH, UchigatanaSheathed),
            Map.entry(MOUNT, mountedSwordMS)
    );

    public static Map<Style, MoveSet> bowMovesets = Map.ofEntries(
            Map.entry(COMMON, rangedMoveSet)
    );

    public static Map<Style, MoveSet> greatswordMovesets = Map.ofEntries(
            Map.entry(TWO_HAND, greatsword2HMS)
    );

    public static Map<Style, MoveSet> longswordMovesets = Map.ofEntries(
            Map.entry(OCHS, LiechtenauerMS),
            Map.entry(ONE_HAND, longsword1HMS),
            Map.entry(TWO_HAND, longsword2HMS),
            Map.entry(MOUNT, mountedSwordMS)
    );

    public static Map<Style, MoveSet> spearMovesets = Map.ofEntries(
            Map.entry(ONE_HAND, spear1HMS),
            Map.entry(TWO_HAND, spear2HMS),
            Map.entry(MOUNT, mountedSpearMS)
    );

    public static Map<Style, MoveSet> tachiMovesets = Map.ofEntries(
            Map.entry(TWO_HAND, tachi2HMS),
            Map.entry(Styles.MOUNT, mountedSwordMS)
    );

    public static Map<Style, MoveSet> spellMovesets = null;

    static
    {
        if (ModList.get().isLoaded(ManaAndArtificeMod.ID))
        {
            spellMovesets = Map.ofEntries(
                    Map.entry(ONE_HAND, baseSpellMS)
            );
        }
    }

}
