package com.red.masaadditions.tweakeroo_additions.mixin;

import com.red.masaadditions.tweakeroo_additions.config.FeatureToggleExtended;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorseEntity.class)
public abstract class MixinLlamaEntity extends AnimalEntity {
    protected MixinLlamaEntity(EntityType<? extends AbstractDonkeyEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getControllingPassenger()Lnet/minecraft/entity/LivingEntity;", at = @At("HEAD"), cancellable = true)
    private void allowLamaSteering(CallbackInfoReturnable<LivingEntity> cir) {
        if (this.getType().equals(EntityType.LLAMA)  && FeatureToggleExtended.TWEAK_LLAMA_STEERING.getBooleanValue() && !this.getBodyArmor().isEmpty()) {
            Entity var2 = this.getFirstPassenger();
            if (var2 instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity)var2;
                cir.setReturnValue(playerEntity);
            };
        }
    }
}
