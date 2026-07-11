package com.red.masaadditions.tweakeroo_additions.mixin;

import com.red.masaadditions.tweakeroo_additions.config.ConfigsExtended;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen {

    @Inject(method = "init", at = @At("TAIL"))
    private void disableRealmsButton(CallbackInfo ci) {
        if (!ConfigsExtended.Disable.DISABLE_REALMS_BUTTON.getBooleanValue()) {
            return;
        }

        TitleScreen screen = (TitleScreen)(Object)this;

        List<ClickableWidget> toRemove = new ArrayList<>();

        for (var child : screen.children()) {
            if (child instanceof ClickableWidget widget &&
                    widget.getMessage().equals(Text.translatable("menu.online"))) {
                toRemove.add(widget);
            }
        }

        ScreenAccessor accessor = (ScreenAccessor) screen;
        toRemove.forEach(accessor::masaadditions$remove);
    }
}