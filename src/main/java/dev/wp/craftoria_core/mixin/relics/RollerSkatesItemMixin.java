package dev.wp.craftoria_core.mixin.relics;

import it.hurts.sskirillss.relics.items.relics.feet.RollerSkatesItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotContext;

@Mixin(RollerSkatesItem.class)
public class RollerSkatesItemMixin {
    // Yeet roller skates as they make players go mach 10 with helium flamingo
    // Too lazy to figure out a proper fix, so I'll just remove them entirely
    // until either the mod author fixes it or I find a better solution
    @Inject(method = "curioTick", at = @At("HEAD"), cancellable = true)
    private void curioTickInject(SlotContext slotContext, ItemStack stack, CallbackInfo ci) {
        stack.setCount(0);
        ci.cancel();
    }
}
