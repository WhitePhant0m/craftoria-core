package dev.wp.craftoria_core.ae2.init;

import appeng.api.stacks.AEKeyType;
import dev.wp.craftoria_core.ae2.item.cell.BlackHoleCellItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static dev.wp.craftoria_core.CItems.ITEMS;

public class AE2Items {
    // Cell Parts / Housings
    public static final DeferredItem<Item> BLACK_HOLE_CORE = ITEMS.register("black_hole_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLACK_HOLE_CELL_HOUSING = ITEMS.register("black_hole_cell_housing", () -> new Item(new Item.Properties()));

    // Cells
    public static final DeferredItem<BlackHoleCellItem> FLUID_BLACK_HOLE_CELL = ITEMS.register("fluid_black_hole_cell", () -> new BlackHoleCellItem(
            new Item.Properties(), AEKeyType.fluids(), BLACK_HOLE_CORE, BLACK_HOLE_CELL_HOUSING));

    public static void init() {
    }
}
