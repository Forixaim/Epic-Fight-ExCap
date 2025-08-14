package net.forixaim.ex_cap.skill;

import net.forixaim.ex_cap.EpicFightEXCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.utils.PacketBufferCodec;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.BasicAttack;
import yesman.epicfight.skill.SkillDataKey;

public class ExCapDatakeys
{
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(ResourceLocation.fromNamespaceAndPath(EpicFightMod.MODID, "skill_data_keys"), EpicFightEXCapability.MODID);
    public static final RegistryObject<SkillDataKey<Integer>> COMBO_COUNTER = DATA_KEYS.register("combo_counter", () -> SkillDataKey.createSkillDataKey(PacketBufferCodec.INTEGER, 0, true, BasicAttack.class));


}
