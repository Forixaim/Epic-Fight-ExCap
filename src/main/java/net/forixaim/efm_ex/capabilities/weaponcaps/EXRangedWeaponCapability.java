package net.forixaim.efm_ex.capabilities.weaponcaps;

import com.google.common.collect.Maps;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.api.moveset.RangedMoveSet;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.Map;

public class EXRangedWeaponCapability extends EXWeaponCapability
{
	protected final Map<Style, Map<LivingMotion, AnimationProvider<?>>> rangeAnimationModifiers;

	public EXRangedWeaponCapability(CapabilityItem.Builder builder) {
		super(builder);
		EXRangedWeaponCapability.Builder rangedBuilder = (EXRangedWeaponCapability.Builder)builder;
		this.rangeAnimationModifiers = rangedBuilder.rangeAnimationModifiers;
	}

	public void setConfigFileAttribute(double armorNegation1, double impact1, int maxStrikes1, double armorNegation2, double impact2, int maxStrikes2) {
		this.addStyleAttributes(Styles.RANGED, armorNegation1, impact1, maxStrikes1);
	}

	public Map<LivingMotion, AnimationProvider<?>> getLivingMotionModifier(LivingEntityPatch<?> playerdata, InteractionHand hand) {
		return hand == InteractionHand.MAIN_HAND ?
				this.rangeAnimationModifiers.computeIfAbsent(getStyle(playerdata),
						style -> this.rangeAnimationModifiers.get(Styles.COMMON))
				:
				super.getLivingMotionModifier(playerdata, hand);
	}

	public boolean availableOnHorse() {
		return true;
	}

	public boolean canBePlacedOffhand() {
		return false;
	}

	public static EXRangedWeaponCapability.Builder builder() {
		return new EXRangedWeaponCapability.Builder();
	}

	public CapabilityItem.ZoomInType getZoomInType() {
		return this.zoomInType;
	}

	public static class Builder extends EXWeaponCapability.Builder {
		private final Map<Style, Map<LivingMotion, AnimationProvider<?>>> rangeAnimationModifiers;

		protected Builder() {
			category(CapabilityItem.WeaponCategories.RANGED);
			constructor(EXRangedWeaponCapability::new);
			this.rangeAnimationModifiers = Maps.newHashMap();
		}

		public EXRangedWeaponCapability.Builder addAnimationsModifier(Style wieldStyle, LivingMotion livingMotion, AnimationProvider<?> animations) {
			this.rangeAnimationModifiers.computeIfAbsent(wieldStyle, style -> Maps.newHashMap());
			this.rangeAnimationModifiers.get(wieldStyle).put(livingMotion, animations);
			return this;
		}

		public EXRangedWeaponCapability.Builder zoomInType( ZoomInType zoomInType) {
			super.zoomInType(zoomInType);
			return this;
		}

		@Override
		public void addMoveset(Style style, MoveSet moveSet) {
			super.addMoveset(style, moveSet);
			if (moveSet instanceof RangedMoveSet rms)
			{
				rms.getRangedAttackModifiers().forEach((livingMotion, animationProvider) -> this.addAnimationsModifier(style, livingMotion, animationProvider));
			}
		}
	}
}
