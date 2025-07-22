package dev.wp.craftoria_core.ae2.compat;

import dev.wp.craftoria_core.ae2.init.AE2Items;
import dev.wp.craftoria_core.ae2.item.cell.BlackHoleCellItem;
import me.ramidzkh.mekae2.ae2.MekanismKeyType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static dev.wp.craftoria_core.CItems.ITEMS;

public class AppMekItems {
    public static final DeferredItem<Item> CHEMICAL_CORE = ITEMS.register("chemical_core", () -> new Item(new Item.Properties()));

    public static final DeferredItem<BlackHoleCellItem> FE_BLACK_HOLE_CELL = ITEMS.register("chemical_black_hole_cell", () -> new BlackHoleCellItem(
            new Item.Properties(), MekanismKeyType.TYPE, CHEMICAL_CORE, AE2Items.BLACK_HOLE_CELL_HOUSING));

    public static void init() {
    }
}
