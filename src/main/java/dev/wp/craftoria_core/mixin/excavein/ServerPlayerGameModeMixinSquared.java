package dev.wp.craftoria_core.mixin.excavein;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.excavein.data.SelectionPlayerData;

import static dev.wp.craftoria_core.util.Utils.excaveinCheck;
import static uwu.lopyluna.excavein.tracker.ExcaveinTacker.getSelectionData;

@Mixin(value = ServerPlayerGameMode.class, priority = 1500)
public class ServerPlayerGameModeMixinSquared {
    @Shadow
    @Final
    protected ServerPlayer player;
    @Shadow
    private GameType gameModeForPlayer = GameType.DEFAULT_MODE;

    @TargetHandler(mixin = "uwu.lopyluna.excavein.mixins.ServerPlayerGameModeMixin", name = "destroyBlock")
    @WrapMethod(method = "@MixinSquared:Handler")
    private void destroyBlock(BlockPos pPos, CallbackInfoReturnable<Boolean> cir, Operation<Void> original) {
        ServerPlayer pPlayer = player;
        SelectionPlayerData data = getSelectionData(pPlayer.getUUID());
        if (data != null && excaveinCheck(data)) if (data.blockBreak(gameModeForPlayer, pPos)) original.call(pPos, cir);
    }

    @TargetHandler(mixin = "uwu.lopyluna.excavein.mixins.ServerPlayerGameModeMixin", name = "useItemOn")
    @WrapMethod(method = "@MixinSquared:Handler")
    private void useItemOn(ServerPlayer pPlayer, Level pLevel, ItemStack pStack, InteractionHand pHand, BlockHitResult pHitResult, CallbackInfoReturnable<InteractionResult> cir, Operation<Void> original) {
        SelectionPlayerData data = getSelectionData(pPlayer.getUUID());
        if (data != null && excaveinCheck(data)) {
            original.call(pPlayer, pLevel, pStack, pHand, pHitResult, cir);
        }
    }
}
