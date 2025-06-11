package dev.wp.craftoria_core.mixin.xycraft;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.wp.craftoria_core.Craftoria;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(targets = "tv.soaryn.xycraft.core.XyCoreClient$ForgeBus")
public interface XyCoreClientMixin {

    @WrapMethod(method="onTagsUpdated")
    private static void queueClientsideActionsOnClient(TagsUpdatedEvent event, Operation<Void> original) {
        if (!Minecraft.getInstance().isSameThread()) {
            Craftoria.LOGGER.warn("Queuing XYCraft onTagsUpdated clientside tasks to clientside thread executor");
            Minecraft.getInstance().execute(()-> {
                Craftoria.LOGGER.warn("Running XYCraft onTagsUpdated task on render thread instead of server thread");
                Optional.ofNullable(Minecraft.getInstance().player)
                        .map(Entity::registryAccess)
                        .ifPresent(registry -> {
                            original.call( new TagsUpdatedEvent(registry, false, true));
                        });
            });
        }
    }
}