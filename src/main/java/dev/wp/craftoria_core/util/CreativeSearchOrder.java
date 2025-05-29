package dev.wp.craftoria_core.util;

import dev.wp.craftoria_core.Craftoria;
import net.minecraft.client.Minecraft;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CreativeSearchOrder {

    private static final Map<ItemMatcher, Integer> itemOrderMap = new ConcurrentHashMap<>();
    private static volatile boolean updatePending = false;

    private CreativeSearchOrder() {
    }

    public static int getItemIndex(ItemStack item) {
        Integer index = itemOrderMap.get(ItemMatcher.of(item));
        if (index != null) return index;

        index = itemOrderMap.get(ItemMatcher.ignoreNbt(item.getItem()));
        return index != null ? index : Integer.MAX_VALUE;
    }

    public static void scheduleUpdateIfNeeded() {
        if (Utils.emiReloading) {
            Utils.updateBlockedByEmi = true;
            Craftoria.LOGGER.debug("EMI reload in progress; skipping item order update");
            return;
        }

        if (!updatePending) {
            updatePending = true;
            new Thread(CreativeSearchOrder::buildItemOrderMap, Craftoria.NAME + ": rebuilding creative order").start();
        }
    }

    private static void buildItemOrderMap() {
        try {
            Minecraft mc = Minecraft.getInstance();
            if (mc.level == null || mc.player == null) return;

            FeatureFlagSet flags = mc.level.enabledFeatures();
            boolean showOpItems = mc.player.canUseGameMasterBlocks() && mc.options.operatorItemsTab().get();

            CreativeModeTabs.tryRebuildTabContents(flags, !showOpItems, mc.level.registryAccess());
            List<ItemStack> stacks = List.copyOf(CreativeModeTabs.searchTab().getDisplayItems());
            if (stacks.isEmpty()) return;

            Map<ItemMatcher, Integer> newMap = new ConcurrentHashMap<>(stacks.size() * 2);
            int index = 0;

            for (ItemStack stack : stacks) {
                Item item = stack.getItem();
                ItemMatcher noNbt = ItemMatcher.ignoreNbt(item);
                if (!stack.hasFoil() || !newMap.containsKey(noNbt)) {
                    newMap.put(noNbt, index++);
                }
                newMap.put(ItemMatcher.of(stack), index++);
            }

            itemOrderMap.clear();
            itemOrderMap.putAll(newMap);
        } finally {
            updatePending = false;
        }
    }
}
