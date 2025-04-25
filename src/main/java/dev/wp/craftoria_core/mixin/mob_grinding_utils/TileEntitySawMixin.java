package dev.wp.craftoria_core.mixin.mob_grinding_utils;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import mob_grinding_utils.tile.TileEntitySaw;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TileEntitySaw.class)
public class TileEntitySawMixin {
    @ModifyReturnValue(method = "canTakeItemThroughFace", at = @At("RETURN"))
    private boolean canTakeItemThroughFace(boolean original) {
        return false;
    }

    @ModifyReturnValue(method = "canPlaceItemThroughFace", at = @At("RETURN"))
    private boolean canPlaceItemThroughFace(boolean original) {
        return false;
    }
}
