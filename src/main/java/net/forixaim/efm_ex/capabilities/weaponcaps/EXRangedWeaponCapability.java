package net.forixaim.efm_ex.capabilities.weaponcaps;

import com.google.common.collect.Maps;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.RangedWeaponCapability;

import java.util.Map;
import java.util.List;

public class EXRangedWeaponCapability extends EXWeaponCapability
{
	protected Map<LivingMotion, AnimationProvider<?>> rangeAnimationModifiers;
	protected CapabilityItem.ZoomInType zoomInType;

	protected EXRangedWeaponCapability(CapabilityItem.Builder builder) {
		super(builder);
		EXRangedWeaponCapability.Builder rangedBuilder = (EXRangedWeaponCapability.Builder)builder;
		this.rangeAnimationModifiers = rangedBuilder.rangeAnimationModifiers;
		this.zoomInType = rangedBuilder.zoomInType;
	}

	public void setConfigFileAttribute(double armorNegation1, double impact1, int maxStrikes1, double armorNegation2, double impact2, int maxStrikes2) {
		this.addStyleAttributes(Styles.RANGED, armorNegation1, impact1, maxStrikes1);
	}

	public Map<LivingMotion, AnimationProvider<?>> getLivingMotionModifier(LivingEntityPatch<?> playerdata, InteractionHand hand) {
		return hand == InteractionHand.MAIN_HAND ? this.rangeAnimationModifiers : super.getLivingMotionModifier(playerdata, hand);
	}

	public boolean availableOnHorse() {
		return true;
	}

	public boolean canBePlacedOffhand() {
		return false;
	}

 public List<AnimationProvider<?>> getAutoAttckMotion(PlayerPatch<?> playerpatch) {
		return getBasicAutoAttackMotion();
	}

	public static EXRangedWeaponCapability.Builder builder() {
		return new EXRangedWeaponCapability.Builder();
	}

	public CapabilityItem.ZoomInType getZoomInType() {
		return this.zoomInType;
	}

	public static class Builder extends EXWeaponCapability.Builder {
		private final Map<LivingMotion, AnimationProvider<?>> rangeAnimationModifiers;
		private CapabilityItem.ZoomInType zoomInType;

		protected Builder() {
			this.zoomInType = ZoomInType.USE_TICK;
			category(CapabilityItem.WeaponCategories.RANGED);
			constructor(EXRangedWeaponCapability::new);
			this.rangeAnimationModifiers = Maps.newHashMap();
		}

		public EXRangedWeaponCapability.Builder addAnimationsModifier(LivingMotion livingMotion, AnimationProvider<?> animations) {
			this.rangeAnimationModifiers.put(livingMotion, animations);
			return this;
		}

		public EXRangedWeaponCapability.Builder zoomInType(CapabilityItem.ZoomInType zoomInType) {
			this.zoomInType = zoomInType;
			return this;
		}
	}
}
