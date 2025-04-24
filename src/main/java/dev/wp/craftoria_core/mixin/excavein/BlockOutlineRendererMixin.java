package dev.wp.craftoria_core.mixin.excavein;

import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uwu.lopyluna.excavein.client.BlockOutlineRenderer;
import uwu.lopyluna.excavein.client.ClientHandler;

@Mixin(BlockOutlineRenderer.class)
public class BlockOutlineRendererMixin {

    @Inject(method = "onRenderWorld", at = @At("HEAD"), cancellable = true)
    private static void injectKeyPressedCheck(RenderHighlightEvent.Block event, CallbackInfo ci) {
        if (!ClientHandler.keyPressed) ci.cancel();
    }
}