package net.forixaim.ex_cap.api.moveset;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.*;
import net.forixaim.ex_cap.api.Registries;
import net.forixaim.ex_cap.api.providers.ProviderConditional;
import net.forixaim.ex_cap.api.providers.ProviderConditionalType;
import net.forixaim.ex_cap.api.utilities.JsonUtils;
import net.forixaim.ex_cap.capabilities.ExCapWeapon;
import net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.data.reloader.SkillManager;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.SkillDataKeys;
import yesman.epicfight.skill.SkillSlot;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ExCapWeaponReloadListener extends SimpleJsonResourceReloadListener
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String DIRECTORY = "capabilities/weapons/ex_cap";
    public ExCapWeaponReloadListener() {
        super(GSON, DIRECTORY);
    }

    private void clear()
    {
        ExCapWeapons.REGISTRY.get().forEach( weapon ->
        {
            weapon.getAttackSets().clear();
            weapon.getStyleComboProviderRegistry().clear();
        });
        Registries.registerCapabilities();
    }


    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> json, @NotNull ResourceManager manager, @NotNull ProfilerFiller filler) {
        clear();
        manager.listPacks().forEach(pack ->
                pack.getNamespaces(PackType.SERVER_DATA).forEach(namespace -> {
                    //Lists all weapons
                    ExCapWeapons.REGISTRY.get().forEach(
                            weapon -> {
                                if (Objects.requireNonNull(ExCapWeapons.REGISTRY.get().getKey(weapon)).getNamespace().equals(namespace)) {
                                    Map<ResourceLocation, JsonElement> movesets = JsonUtils.getJsonsForNamespace(manager, namespace, DIRECTORY + "/" + Objects.requireNonNull(ExCapWeapons.REGISTRY.get().getKey(weapon)).getPath() + "/movesets");
                                    Map<ResourceLocation, JsonElement> providers = JsonUtils.getJsonsForNamespace(manager, namespace, DIRECTORY + "/" +  Objects.requireNonNull(ExCapWeapons.REGISTRY.get().getKey(weapon)).getPath() + "/providers");
                                    if (!providers.isEmpty())
                                    {
                                        providers.forEach((key, value) -> {
                                            JsonObject gsonObject = value.getAsJsonObject();
                                            ProviderConditionalType type = ProviderConditionalType.valueOf(gsonObject.get("type").getAsString().toUpperCase());
                                            Style wieldStyle = Style.ENUM_MANAGER.get(gsonObject.get("style").getAsString().toUpperCase());
                                            Boolean visibleOffHand = gsonObject.get("visible_offhand").getAsBoolean();
                                            switch (type) {
                                                case DEFAULT ->
                                                {
                                                    for (int i = 0; i < weapon.getStyleComboProviderRegistry().size(); i++)
                                                    {
                                                        if (weapon.getStyleComboProviderRegistry().get(i).getType().equals(type))
                                                        {
                                                            weapon.getStyleComboProviderRegistry().set(i, ProviderConditional.builder().setType(type).setWieldStyle(wieldStyle).isVisibleOffHand(visibleOffHand).build());
                                                            break;
                                                        }
                                                    }
                                                }
                                                case WEAPON_CATEGORY -> weapon.getStyleComboProviderRegistry().add(ProviderConditional.builder()
                                                        .setType(type).setWieldStyle(wieldStyle).isVisibleOffHand(visibleOffHand)
                                                        .setCategory(WeaponCategory.ENUM_MANAGER.get(gsonObject.get("weapon_category").getAsString().toUpperCase()))
                                                        .setHand(InteractionHand.valueOf(gsonObject.get("hand").getAsString().toUpperCase()))
                                                        .build());
                                                case SPECIFIC_WEAPON -> weapon.getStyleComboProviderRegistry().add(ProviderConditional.builder()
                                                        .setType(type).setWieldStyle(wieldStyle).isVisibleOffHand(visibleOffHand)
                                                        .setWeapon(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(gsonObject.get("specific_weapon").getAsString())))
                                                        .setHand(InteractionHand.valueOf(gsonObject.get("hand").getAsString().toUpperCase()))
                                                        .build());
                                                case SKILL_EXISTENCE -> weapon.getStyleComboProviderRegistry().add(ProviderConditional.builder()
                                                        .setType(type).setWieldStyle(wieldStyle).isVisibleOffHand(visibleOffHand)
                                                        .setSkillToCheck(SkillManager.getSkill(gsonObject.get("skill").getAsString()))
                                                        .setSlot(SkillSlot.ENUM_MANAGER.get(gsonObject.get("slot").getAsString().toUpperCase()))
                                                        .build());
                                                case SKILL_ACTIVATION -> weapon.getStyleComboProviderRegistry().add(ProviderConditional.builder()
                                                        .setType(type).setWieldStyle(wieldStyle).isVisibleOffHand(visibleOffHand)
                                                        .setSkillToCheck(SkillManager.getSkill(gsonObject.get("skill").getAsString()))
                                                        .setSlot(SkillSlot.ENUM_MANAGER.get(gsonObject.get("slot").getAsString().toUpperCase()))
                                                        .build());
                                                case DATA_KEY -> weapon.getStyleComboProviderRegistry().add(ProviderConditional.builder()
                                                        .setType(type).setWieldStyle(wieldStyle).isVisibleOffHand(visibleOffHand)
                                                        .setSkillToCheck(SkillManager.getSkill(gsonObject.get("skill").getAsString()))
                                                        .setKey((SkillDataKey<Boolean>) SkillDataKeys.REGISTRY.get().getValue(ResourceLocation.parse(gsonObject.get("boolean_key").getAsString())))
                                                        .setSlot(SkillSlot.ENUM_MANAGER.get(gsonObject.get("slot").getAsString().toUpperCase()))
                                                        .build());
                                            }
                                        });
                                    }

                                    if (!movesets.isEmpty())
                                    {
                                        movesets.forEach((key, value) ->
                                        {
                                            JsonObject gsonObject = value.getAsJsonObject();
                                            if (gsonObject.has("import"))
                                            {
                                                JsonObject importObject = gsonObject.getAsJsonObject("import");
                                                JsonArray styleArray = importObject.getAsJsonArray("styles");
                                                List<Style> styles = Lists.newArrayList();
                                                styleArray.forEach(styleElement -> styles.add(Style.ENUM_MANAGER.get(styleElement.getAsString())));
                                                String[] resource = importObject.get("parent").getAsString().split(":");
                                                resource[1] = "capabilities/weapons/types" + resource[1];
                                                ResourceLocation location = ResourceLocation.fromNamespaceAndPath(resource[0], resource[1]);
                                                Optional<Resource> importedResource = manager.getResource(location);
                                                JsonObject element = importedResource.map(JsonUtils::parse).orElseGet(JsonObject::new).getAsJsonObject();
                                                Map<String, JsonElement> importedCombos = element.get("combos").getAsJsonObject().asMap();
                                                Map<String, JsonElement> importedInnates = element.get("innate_skills").getAsJsonObject().asMap();
                                                Map<String, JsonElement> importedLivingMotions = element.get("livingmotion_modifier").getAsJsonObject().asMap();
                                                String defaultKey;
                                                if (!importedLivingMotions.isEmpty())
                                                {
                                                    defaultKey = importedLivingMotions.keySet().stream().findFirst().get();
                                                } else
                                                {
                                                    defaultKey = "common";
                                                }
                                                List<MoveSet.MoveSetBuilder> moveSets = Lists.newArrayList();
                                                importedCombos.forEach((s, jsonElement) ->
                                                {
                                                    if (!s.equals("mount"))
                                                    {
                                                        MoveSet.MoveSetBuilder moveSetBuilder = MoveSet.builder();
                                                        List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> autoComboAnimations = getAnimationAccessors(jsonElement);
                                                        moveSetBuilder.addAutoAttacks(autoComboAnimations.toArray(AnimationManager.AnimationAccessor[]::new));
                                                        if (importedCombos.containsKey("mount"))
                                                            moveSetBuilder.addMountAttacks(getAnimationAccessors(importedCombos.get("mount")).toArray(AnimationManager.AnimationAccessor[]::new));
                                                        if (importedInnates.containsKey(s))
                                                            moveSetBuilder.addInnateSkill(itemstack -> SkillManager.getSkill(importedInnates.get(s).getAsString()));
                                                        if (!importedLivingMotions.isEmpty())
                                                            if (importedLivingMotions.containsKey(s))
                                                                getLivingMotionModifiers(importedLivingMotions.get(s).getAsJsonObject().asMap()).forEach(moveSetBuilder::addLivingMotionModifier);
                                                            else
                                                                getLivingMotionModifiers(importedLivingMotions.get(defaultKey).getAsJsonObject().asMap()).forEach(moveSetBuilder::addLivingMotionModifier);
                                                        moveSets.add(moveSetBuilder);
                                                    }
                                                });
                                                importedCombos.remove("mount");
                                                for (int i = 0; i < Math.min(moveSets.size(), styles.size()); i++)
                                                    weapon.getAttackSets().put(styles.get(i), moveSets.get(i).build());
                                            } else
                                            {
                                                MoveSet.MoveSetBuilder moveSetBuilder = MoveSet.builder();

                                                //Fetch required data first
                                                Style wieldStyle = Style.ENUM_MANAGER.get(gsonObject.get("style").getAsString());
                                                //Optionals
                                                if (gsonObject.has("combo_attack"))
                                                {
                                                    moveSetBuilder.addAutoAttacks(getAnimationAccessors(gsonObject.get("combo_attack").getAsJsonArray()).toArray(AnimationManager.AnimationAccessor[]::new));
                                                }
                                                if (gsonObject.has("mount_attack"))
                                                {
                                                    moveSetBuilder.addMountAttacks(getAnimationAccessors(gsonObject.get("mount_attack").getAsJsonArray()).toArray(AnimationManager.AnimationAccessor[]::new));
                                                }
                                                if (gsonObject.has("innate_skill"))
                                                {
                                                    moveSetBuilder.addInnateSkill(itemStack -> SkillManager.getSkill(gsonObject.get("innate_skill").getAsString()));
                                                }
                                                if (gsonObject.has("living_animations"))
                                                {
                                                    getLivingMotionModifiers(gsonObject.get("living_animations").getAsJsonObject().asMap()).forEach(moveSetBuilder::addLivingMotionModifier);
                                                }
                                                if (gsonObject.has("weapon_passive"))
                                                {
                                                    moveSetBuilder.setPassiveSkill(SkillManager.getSkill(gsonObject.get("weapon_passive").getAsString()));
                                                }
                                                if (gsonObject.has("guard_motions"))
                                                {
                                                    gsonObject.get("guard_motions").getAsJsonObject().asMap().forEach((s, jsonElement) ->
                                                    {
                                                        if (SkillManager.getSkill(s) != null)
                                                        {
                                                            jsonElement.getAsJsonObject().asMap().forEach((s1, jsonElement1) -> moveSetBuilder.addGuardAnimations(SkillManager.getSkill(s), GuardSkill.BlockType.valueOf(s1.toUpperCase(Locale.ROOT)), getAnimationAccessors(jsonElement1).toArray(AnimationManager.AnimationAccessor[]::new)));
                                                        }
                                                    });
                                                }
                                                if (gsonObject.has("revelation_attack"))
                                                {
                                                    moveSetBuilder.revelationAttack(AnimationManager.byKey(gsonObject.get("revelation_attack").getAsString()));
                                                }

                                                weapon.getAttackSets().put(wieldStyle, moveSetBuilder.build());
                                            }
                                        });
                                    }
                                }
                            }
                    );
            }));
    }

    private static Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> getLivingMotionModifiers(Map<String, JsonElement> map)
    {
        Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> result = Maps.newHashMap();
        map.forEach((key, jsonElement) -> result.put(LivingMotion.ENUM_MANAGER.get(key), AnimationManager.byKey(jsonElement.getAsString())));
        return result;
    }

    private static @NotNull List<AnimationManager.AnimationAccessor<? extends StaticAnimation>> getGuardAccessors(JsonElement jsonElement)
    {
        List<JsonElement> attacks = jsonElement.getAsJsonArray().asList();
        List<AnimationManager.AnimationAccessor<? extends StaticAnimation>> autocomboAnims = Lists.newArrayList();

        attacks.forEach(attacksElement -> {
            autocomboAnims.add(AnimationManager.byKey(attacksElement.getAsString()));
        });
        return autocomboAnims;
    }

    private static @NotNull List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> getAnimationAccessors(JsonElement jsonElement)
    {
        List<JsonElement> attacks = jsonElement.getAsJsonArray().asList();
        List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> autocomboAnims = Lists.newArrayList();

        attacks.forEach(attacksElement -> {
            autocomboAnims.add(AnimationManager.byKey(attacksElement.getAsString()));
        });
        return autocomboAnims;
    }
}