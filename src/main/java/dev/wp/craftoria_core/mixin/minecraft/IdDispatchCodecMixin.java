package dev.wp.craftoria_core.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Cancellable;
import dev.wp.craftoria_core.Craftoria;
import dev.wp.craftoria_core.config.ServerConfig;
import net.minecraft.network.codec.IdDispatchCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IdDispatchCodec.class)
public class IdDispatchCodecMixin {
    @ModifyExpressionValue(method = "encode(Lio/netty/buffer/ByteBuf;Ljava/lang/Object;)V", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2IntMap;getOrDefault(Ljava/lang/Object;I)I"))
    private int shutUp(int original, @Cancellable CallbackInfo ci) {
        // Too much of it in our logs, and I can't find the source of it. Suppressing it.
        if (original == -1 && ServerConfig.suppressUnknownPacketId) {
            ci.cancel();
            Craftoria.LOGGER.debug("Suppressed disconnect error: Unknown packet ID -1");
        }
        return original;
    }
}
