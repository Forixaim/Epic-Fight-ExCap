package net.forixaim.efm_ex.capabilities.weaponcaps;

import com.google.common.collect.Maps;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.api.moveset.RangedMoveSet;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.UseAnim;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.Map;

public class EXRangedWeaponCapability extends EXWeaponCapability implements RangedWeapon
{
	protected final Map<Style, Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>>> rangeAnimationModifiers;

	public EXRangedWeaponCapability(CapabilityItem.Builder builder) {
		super(builder);
		EXRangedWeaponCapability.Builder rangedBuilder = (EXRangedWeaponCapability.Builder)builder;
		this.rangeAnimationModifiers = rangedBuilder.rangeAnimationModifiers;
	}

	@Override
	public UseAnim getUseAnimation(LivingEntityPatch<?> playerpatch) {
		return super.getUseAnimation(playerpatch);
	}

	public Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> getLivingMotionModifier(LivingEntityPatch<?> playerdata, InteractionHand hand) {
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
		private final Map<Style, Map<LivingMotion,AnimationManager.AnimationAccessor<? extends StaticAnimation>>> rangeAnimationModifiers;

		protected Builder() {
			category(CapabilityItem.WeaponCategories.RANGED);
			constructor(EXRangedWeaponCapability::new);
			this.rangeAnimationModifiers = Maps.newHashMap();
		}

		public EXRangedWeaponCapability.Builder addAnimationsModifier(Style wieldStyle, LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animations) {
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
