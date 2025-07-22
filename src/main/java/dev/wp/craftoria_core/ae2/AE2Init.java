package dev.wp.craftoria_core.ae2;

import appeng.api.storage.StorageCells;
import dev.wp.craftoria_core.ae2.compat.AppFluxItems;
import dev.wp.craftoria_core.ae2.compat.AppMekItems;
import dev.wp.craftoria_core.ae2.compat.ArsEngItems;
import dev.wp.craftoria_core.ae2.init.AE2Components;
import dev.wp.craftoria_core.ae2.init.AE2Items;
import dev.wp.craftoria_core.ae2.item.cell.BlackHoleCellItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public class AE2Init {
    private static final boolean isAppFluxLoaded = ModList.get().isLoaded("appflux");
    private static final boolean isAppMekLoaded = ModList.get().isLoaded("appmek");
    private static final boolean isArsEngLoaded = ModList.get().isLoaded("arseng");

    public static void init(IEventBus bus) {
        AE2Items.init();
        AE2Components.init();

        if (isAppFluxLoaded) AppFluxItems.init();
        if (isAppMekLoaded) AppMekItems.init();
        if (isArsEngLoaded) ArsEngItems.init();

        bus.addListener(AE2Init::initStorageCells);
    }

    private static void initStorageCells(FMLCommonSetupEvent event) {
        StorageCells.addCellHandler(BlackHoleCellItem.HANDLER);
    }
}
