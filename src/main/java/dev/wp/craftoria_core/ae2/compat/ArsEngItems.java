package dev.wp.craftoria_core.ae2.compat;

import dev.wp.craftoria_core.ae2.init.AE2Items;
import dev.wp.craftoria_core.ae2.item.cell.BlackHoleCellItem;
import gripe._90.arseng.me.key.SourceKeyType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static dev.wp.craftoria_core.CItems.ITEMS;

public class ArsEngItems {
    public static final DeferredItem<Item> SOURCE_CORE = ITEMS.register("source_core", () -> new Item(new Item.Properties()));

    public static final DeferredItem<BlackHoleCellItem> SOURCE_BLACK_HOLE_CELL = ITEMS.register("source_black_hole_cell", () -> new BlackHoleCellItem(
            new Item.Properties(), SourceKeyType.TYPE, SOURCE_CORE, AE2Items.BLACK_HOLE_CELL_HOUSING));

    public static void init() {
    }
}
