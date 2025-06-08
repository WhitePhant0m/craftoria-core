package dev.wp.craftoria_core.mixin.cable_facades;

import com.portingdeadmods.cable_facades.CFConfig;
import com.portingdeadmods.cable_facades.events.server.ServerInGameEvents;
import com.portingdeadmods.cable_facades.registries.CFItems;
import com.portingdeadmods.cable_facades.utils.FacadeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ServerInGameEvents.class)
public class ServerInGameEventsMixin {

    /**
     * @author WP
     * @reason erm, idk.
     */
    @SubscribeEvent
    @Overwrite
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Level level = event.getPlayer().level();
        BlockPos pos = event.getPos();
        if (FacadeUtils.hasFacade(level, pos)) {
            if (!level.isClientSide()) {
                BlockState facade = FacadeUtils.getFacade(level, pos);
                FacadeUtils.removeFacade(level, pos);
                Player player = event.getPlayer();
                if (!player.isCreative() && CFConfig.consumeFacade) {
                    ItemStack facadeStack = CFItems.FACADE.get().createFacade(facade.getBlock());
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), facadeStack);
                }

                event.setCanceled(true);
            }
            FacadeUtils.updateBlocks(level, pos);
        }
    }
}

