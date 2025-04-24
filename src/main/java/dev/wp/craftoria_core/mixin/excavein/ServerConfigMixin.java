package dev.wp.craftoria_core.mixin.excavein;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import uwu.lopyluna.excavein.config.ServerConfig;

@Mixin(ServerConfig.class)
public class ServerConfigMixin {

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;", ordinal = 6))
    private static String fixHeartConsumeAmountComment(String original) {
        return "Amount of Hearts to be taken when mining for said block in selection (default = 0)";
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;defineInRange(Ljava/lang/String;III)Lnet/neoforged/neoforge/common/ModConfigSpec$IntValue;", ordinal = 4))
    private static void fixHeartConsumeAmountConfig(Args args) {
        args.set(0, "HealthConsumeAmount");
        args.set(1, 0);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;", ordinal = 7))
    private static String fixHeartConsumeAmountAddComment(String original) {
        return "Amount of Hearts to be taken after mining (default = 0)";
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;defineInRange(Ljava/lang/String;III)Lnet/neoforged/neoforge/common/ModConfigSpec$IntValue;", ordinal = 5), index = 0)
    private static String fixHeartConsumeAddAmountConfig(String original) {
        return "HealthConsumeAddedAmount";
    }
}
