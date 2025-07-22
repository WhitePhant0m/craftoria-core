package dev.wp.craftoria_core.ae2.compat;

import com.glodblock.github.appflux.common.me.key.type.FluxKeyType;
import dev.wp.craftoria_core.ae2.init.AE2Items;
import dev.wp.craftoria_core.ae2.item.cell.BlackHoleCellItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static dev.wp.craftoria_core.CItems.ITEMS;

public class AppFluxItems {
    public static final DeferredItem<Item> FLUX_CORE = ITEMS.register("flux_core", () -> new Item(new Item.Properties()));

    public static final DeferredItem<BlackHoleCellItem> FE_BLACK_HOLE_CELL = ITEMS.register("flux_black_hole_cell", () -> new BlackHoleCellItem(
            new Item.Properties(), FluxKeyType.TYPE, FLUX_CORE, AE2Items.BLACK_HOLE_CELL_HOUSING));

    public static void init() {
    }
}
