package io.izzel.arclight.mixin.core.entity.monster;

import io.izzel.arclight.bridge.entity.MobEntityBridge;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.entity.monster.ZombiePigmanEntity.HurtByAggressorGoal")
public class ZombiePigmanEntity_HurtByAggressorGoalMixin {

    @Inject(method = "setAttackTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/MobEntity;setAttackTarget(Lnet/minecraft/entity/LivingEntity;)V"))
    private void arclight$reason(MobEntity mobIn, LivingEntity targetIn, CallbackInfo ci) {
        ((MobEntityBridge) mobIn).bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, true);
    }
}
