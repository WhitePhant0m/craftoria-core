package dev.wp.craftoria_core.util;

import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.util.FakePlayer;
import uwu.lopyluna.excavein.data.SelectionPlayerData;

public class Utils {
    public static final boolean mekanismLoaded = isModLoaded("mekanism");

    public static volatile boolean emiReloading = false;
    public static volatile boolean updateBlockedByEmi = false;

    private final static int fluidOffset = Integer.MAX_VALUE / 1000;
    private final static int gasOffset = fluidOffset * 10;

    public static int getItemPosition(Item item) {
        if (emiReloading) return BuiltInRegistries.ITEM.getId(item); // EMI reload in progress, use a fallback
        else return CreativeSearchOrder.getItemIndex(item);
    }

    public static int getFluidPos(Fluid fluid) {
        return fluidOffset + BuiltInRegistries.FLUID.getId(fluid);
    }

    public static int getChemicalPos(Chemical chemical) {
        if (!mekanismLoaded) return 0;
        return gasOffset + MekanismAPI.CHEMICAL_REGISTRY.getId(chemical);
    }

    public static boolean excaveinCheck(SelectionPlayerData data) {
        return data.getLevel() != null && data.getPlayer() != null && data.getPlayerUUID() != null && !(data.getPlayer() instanceof FakePlayer);
    }

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}
