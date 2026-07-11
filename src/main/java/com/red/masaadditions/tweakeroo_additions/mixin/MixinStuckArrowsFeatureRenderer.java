package com.red.masaadditions.tweakeroo_additions.mixin;

import com.red.masaadditions.tweakeroo_additions.config.ConfigsExtended;
import fi.dy.masa.tweakeroo.Tweakeroo;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.StuckArrowsFeatureRenderer;
import net.minecraft.client.render.entity.feature.StuckObjectsFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StuckObjectsFeatureRenderer.class)
public abstract class MixinStuckArrowsFeatureRenderer {
    @Inject(method = "renderObject", at = @At("HEAD"), cancellable = true)
    private void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float f, float directionX, float directionY, CallbackInfo ci) {
        if (ConfigsExtended.Disable.DISABLE_STUCK_ARROWS_RENDERING.getBooleanValue() && (Object)this instanceof StuckArrowsFeatureRenderer<?>)
            ci.cancel();
    }
}