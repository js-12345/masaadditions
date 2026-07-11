package com.red.masaadditions.tweakeroo_additions.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.red.masaadditions.tweakeroo_additions.config.ConfigsExtended;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.client.render.entity.state.ItemFrameEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemFrameEntityRenderer.class)
public class MixinItemFrameEntityRenderer {
    private boolean isInvisible;

    @Inject(
            method = "render(Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/ItemFrameEntityRenderer;getPositionOffset(Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;)Lnet/minecraft/util/math/Vec3d;",
                    shift = At.Shift.AFTER
            )
    )
    private void disableItemFrameFrameRendering(ItemFrameEntityRenderState itemFrameEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        isInvisible = ConfigsExtended.Disable.DISABLE_ITEM_FRAME_FRAME_RENDERING.getBooleanValue() && !itemFrameEntityRenderState.itemRenderState.isEmpty() || itemFrameEntityRenderState.invisible;
    }

    @ModifyExpressionValue(
            method = "render(Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;invisible:Z"
            )
    )
    private boolean disableItemFrameFrameRendering(boolean original) {
        return isInvisible;
    }
}
