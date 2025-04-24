package dev.wp.craftoria_core.mixin.skillsmod;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.puffish.skillsmod.api.SkillsAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkillsAPI.class)
public abstract class SkillsAPIMixin {

    @Inject(method = "updateExperienceSources(Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Function;)V", at = @At("HEAD"), cancellable = true)
    private static void skipFakePlayers(CallbackInfo ci, @Local(argsOnly = true) ServerPlayer player) {
        if (player instanceof FakePlayer) ci.cancel();
    }

    @Inject(method = "updateExperienceSources(Lnet/minecraft/server/level/ServerPlayer;Ljava/lang/Class;Ljava/util/function/Function;)V", at = @At("HEAD"), cancellable = true)
    private static void skipFakePlayers2(CallbackInfo ci, @Local(argsOnly = true) ServerPlayer player) {
        if (player instanceof FakePlayer) ci.cancel();
    }
}
