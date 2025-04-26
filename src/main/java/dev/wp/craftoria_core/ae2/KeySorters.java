package dev.wp.craftoria_core.ae2;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import dev.wp.craftoria_core.util.Utils;
import me.ramidzkh.mekae2.ae2.MekanismKey;
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
        return switch (aeKey) {
            case AEItemKey item -> Utils.getItemPosition(item.toStack());
            case AEFluidKey fluid -> Utils.getFluidPos(fluid.getFluid());
            default -> {
                if (Utils.isModLoaded("appmek") && aeKey instanceof MekanismKey chemicalKey)
                    yield Utils.getChemicalPos((chemicalKey).getStack().getChemical());

                yield Integer.MAX_VALUE;
            }
        };
    }
}
