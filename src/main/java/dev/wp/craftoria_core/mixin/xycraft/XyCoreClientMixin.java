package dev.wp.craftoria_core.mixin.xycraft;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "tv.soaryn.xycraft.core.XyCoreClient$ForgeBus")
public interface XyCoreClientMixin {
    /**
     * @author WP
     * @reason Bad Xycraft, bad!
     */
    @SubscribeEvent
    @Overwrite
    static void onTagsUpdated(TagsUpdatedEvent event) {
    }
}