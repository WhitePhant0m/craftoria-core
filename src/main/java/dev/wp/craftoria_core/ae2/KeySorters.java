package dev.wp.craftoria_core.ae2;

import appeng.api.stacks.AEKey;
import dev.wp.craftoria_core.util.Utils;
import mekanism.api.chemical.Chemical;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Map;

@NonExtendable
@Internal
public interface KeySorters {
    static Comparator<AEKey> comparator() {
        Map<AEKey, Integer> positionCache = new IdentityHashMap<>();

        return Comparator.comparingInt(key -> positionCache.computeIfAbsent(key, KeySorters::applyAsInt));
    }

    static int applyAsInt(AEKey aeKey) {
        Object primaryKey = aeKey.getPrimaryKey();
        return switch (primaryKey) {
            case Item item -> Utils.getItemPosition(item);
            case Fluid fluid -> Utils.getFluidPos(fluid);
            case Chemical chemical when Utils.mekanismLoaded -> Utils.getChemicalPos(chemical);
            case null, default -> Integer.MAX_VALUE;
        };
    }
}
