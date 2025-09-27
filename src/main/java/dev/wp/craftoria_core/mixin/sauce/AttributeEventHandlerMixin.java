package dev.wp.craftoria_core.mixin.sauce;

import com.alexthw.sauce.event.AttributeEventHandler;
import com.hollingsworth.arsnouveau.api.event.SpellModifierEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AttributeEventHandler.class)
public class AttributeEventHandlerMixin {
    @Inject(method = "empowerBySchool", at = @At("HEAD"), cancellable = true)
    private static void empowerBySchoolInject(SpellModifierEvent event, CallbackInfo ci) {
        if (event.spellPart == null) ci.cancel();
    }
}
