package dev.wp.craftoria_core.mixin.mynethersdelight;

import com.llamalad7.mixinextras.sugar.Local;
import com.soytutta.mynethersdelight.common.events.CommonEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CommonEvent.class)
public class CommonEventMixin {

    @ModifyArg(method = "livingDie", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;die(Lnet/minecraft/world/damagesource/DamageSource;)V"))
    private static DamageSource dontNull(DamageSource par1, @Local PiglinBrute Hunter) {
        return Hunter.damageSources().mobAttack(Hunter);
    }
}
