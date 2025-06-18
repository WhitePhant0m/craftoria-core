package dev.wp.craftoria_core.mixin.jdt;

import com.direwolf20.justdirethings.common.entities.CreatureCatcherEntity;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreatureCatcherEntity.class)
public class CreatureCatcherEntityMixin {
    @Unique
    private static final EntityDataAccessor<Boolean> BEING_CAPTURED = SynchedEntityData.defineId(Mob.class, EntityDataSerializers.BOOLEAN);

    @Inject(method = "canCapture", at = @At(value = "RETURN"), cancellable = true)
    private void onCanCapture(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getEntityData().get(BEING_CAPTURED)) cir.setReturnValue(false);
        else entity.getEntityData().set(BEING_CAPTURED, true);
    }

    @Inject(method = "releaseEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;moveTo(Lnet/minecraft/world/phys/Vec3;)V"))
    private void onReleaseEntity(ItemStack itemStack, CallbackInfo ci, @Local Entity entity) {
        entity.getEntityData().set(BEING_CAPTURED, false);
    }
}
