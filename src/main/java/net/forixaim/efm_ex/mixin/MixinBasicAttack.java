package net.forixaim.efm_ex.mixin;

import com.mojang.logging.LogUtils;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.forixaim.efm_ex.skill.ExCapDatakeys;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PlayerRideableJumping;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.*;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.BasicAttackEvent;
import yesman.epicfight.world.entity.eventlistener.ComboCounterHandleEvent;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;
import yesman.epicfight.world.entity.eventlistener.SkillConsumeEvent;

import java.util.List;
import java.util.UUID;

import static yesman.epicfight.skill.BasicAttack.setComboCounterWithEvent;

@Mixin(value = BasicAttack.class, remap = false)
public class MixinBasicAttack
{
    @Shadow @Final private static UUID EVENT_UUID;

    @Unique
    private static void exCap$setComboCounterWithEventEX(ComboCounterHandleEvent.Causal reason, ServerPlayerPatch playerpatch, SkillContainer container, AnimationManager.AnimationAccessor<? extends StaticAnimation> causalAnimation, int value)
    {
        int prevValue = container.getDataManager().getDataValue(SkillDataKeys.COMBO_COUNTER.get());
        ComboCounterHandleEvent comboResetEvent = new ComboCounterHandleEvent(reason, playerpatch, causalAnimation, prevValue, value);
        container.getExecutor().getEventListener().triggerEvents(PlayerEventListener.EventType.COMBO_COUNTER_HANDLE_EVENT, comboResetEvent);
        container.getDataManager().setData(ExCapDatakeys.COMBO_COUNTER.get(), comboResetEvent.getNextValue());
    }

    @Inject(method = "updateContainer", at = @At("RETURN"), remap = false)
    public void updateContainer(SkillContainer container, CallbackInfo ci)
    {
        if (!container.getExecutor().isLogicalClient() && container.getExecutor().getTickSinceLastAction() > 16 && container.getDataManager().getDataValue(ExCapDatakeys.COMBO_COUNTER.get()) > 0) {
            exCap$setComboCounterWithEventEX(ComboCounterHandleEvent.Causal.TIME_EXPIRED, container.getServerExecutor(), container, null, 0);
        }
    }

    @Inject(method = "executeOnServer", at = @At("HEAD"), remap = false, cancellable = true)
    public void executeOnServer(SkillContainer skillContainer, FriendlyByteBuf args, CallbackInfo ci)
    {

        if (skillContainer.getServerExecutor().getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability eX)
        {
            ServerPlayerPatch executor = skillContainer.getServerExecutor();
            SkillConsumeEvent event = new SkillConsumeEvent(executor, (BasicAttack)(Object)this, ((BasicAttack)(Object)this).getResourceType());
            executor.getEventListener().triggerEvents(PlayerEventListener.EventType.SKILL_CONSUME_EVENT, event);
            if (!event.isCanceled()) {
                event.getResourceType().consumer.consume(skillContainer, executor, event.getAmount());
            }

            if (!executor.getEventListener().triggerEvents(PlayerEventListener.EventType.BASIC_ATTACK_EVENT, new BasicAttackEvent(executor))) {
                AnimationManager.AnimationAccessor<? extends AttackAnimation> attackMotion = null;
                ServerPlayer player = executor.getOriginal();
                SkillDataManager dataManager = skillContainer.getDataManager();
                int comboCounter = dataManager.getDataValue(SkillDataKeys.COMBO_COUNTER.get());
                int mountedComboCounter = dataManager.getDataValue(ExCapDatakeys.COMBO_COUNTER.get());
                if (player.isPassenger()) {
                    Entity entity = player.getVehicle();
                    if (entity instanceof PlayerRideableJumping rideable) {
                        if (rideable.canJump() && eX.availableOnHorse() && eX.getMountAttackEX(eX.getStyle(executor)) != null) {
                            List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> combo = eX.getMountAttackEX(eX.getStyle(executor));
                            if (combo == null) {
                                return;
                            }
                            int comboSize = combo.size();
                            mountedComboCounter %= comboSize;
                            attackMotion = combo.get(mountedComboCounter);
                            mountedComboCounter++;
                            exCap$setComboCounterWithEventEX(ComboCounterHandleEvent.Causal.ANOTHER_ACTION_ANIMATION, executor, skillContainer, attackMotion, mountedComboCounter);
                            LogUtils.getLogger().debug("Combo Count: {}", mountedComboCounter);
                        }
                    }
                } else {
                    List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> combo = eX.getAutoAttackMotion(executor);
                    if (combo == null) {
                        return;
                    }

                    int comboSize = combo.size();
                    boolean dashAttack = player.isSprinting();
                    if (dashAttack) {
                        comboCounter = comboSize - 2;
                    } else {
                        comboCounter %= comboSize - 2;
                    }

                    attackMotion = combo.get(comboCounter);
                    comboCounter = dashAttack ? 0 : comboCounter + 1;
                    setComboCounterWithEvent(ComboCounterHandleEvent.Causal.ANOTHER_ACTION_ANIMATION, executor, skillContainer, attackMotion, comboCounter);

                }

                if (attackMotion != null) {
                    executor.playAnimationSynchronized(attackMotion, 0.0F);
                }

                executor.updateEntityState();
            }
            ci.cancel();
        }
    }
}
