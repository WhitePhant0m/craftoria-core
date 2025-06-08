package dev.wp.craftoria_core.mixin.apoth;

import com.llamalad7.mixinextras.sugar.Local;
import dev.shadowsoffire.apothic_enchanting.objects.EnderLeadItem;
import dev.shadowsoffire.apothic_spawners.ASObjects;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderLeadItem.class)
public class EnderLeadMixin {

    @Inject(
            method = "useOn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Spawner;setEntityId(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/util/RandomSource;)V"),
            cancellable = true
    )
    private void onUseOn(UseOnContext ctx, CallbackInfoReturnable<InteractionResult> cir, @Local EntityType<?> type) {
        if (type.is(ASObjects.BLACKLISTED_FROM_SPAWNERS)) cir.setReturnValue(InteractionResult.PASS);
    }
}
