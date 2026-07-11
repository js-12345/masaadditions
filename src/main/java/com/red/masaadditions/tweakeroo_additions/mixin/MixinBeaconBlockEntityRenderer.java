package com.red.masaadditions.tweakeroo_additions.mixin;

import com.red.masaadditions.tweakeroo_additions.config.ConfigsExtended;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntityRenderer.class)
public abstract class MixinBeaconBlockEntityRenderer {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(CallbackInfo ci) {
        if (ConfigsExtended.Disable.DISABLE_BEACON_BEAM_RENDERING.getBooleanValue())
            ci.cancel();
    }
}
