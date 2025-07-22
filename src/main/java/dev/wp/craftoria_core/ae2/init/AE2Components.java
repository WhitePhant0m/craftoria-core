package dev.wp.craftoria_core.ae2.init;

import dev.wp.craftoria_core.ae2.item.cell.BlackHoleStorage;
import net.minecraft.core.component.DataComponentType;

import java.util.function.Consumer;

import static dev.wp.craftoria_core.CComponents.COMP;

public class AE2Components {
    public static final DataComponentType<BlackHoleStorage> SINGULARITY_STORAGE =
            register("singularity_storage", builder -> builder
                    .persistent(BlackHoleStorage.CODEC)
                    .networkSynchronized(BlackHoleStorage.STREAM_CODEC));

    private static <T> DataComponentType<T> register(
            String name, Consumer<DataComponentType.Builder<T>> customizer) {
        var builder = DataComponentType.<T>builder();
        customizer.accept(builder);
        var componentType = builder.build();
        COMP.register(name, () -> componentType);
        return componentType;
    }

    public static void init() {
    }
}
