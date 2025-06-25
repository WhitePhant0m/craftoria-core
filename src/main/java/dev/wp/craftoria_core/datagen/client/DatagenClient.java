package dev.wp.craftoria_core.datagen.client;

import net.minecraft.data.DataProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.function.Function;

public final class DatagenClient {
    public static void configure(GatherDataEvent event) {
        add(event, Lang::new);
    }

    private static void add(GatherDataEvent event, Function<GatherDataEvent, DataProvider> provider) {
        event.getGenerator().addProvider(event.includeClient(), provider.apply(event));
    }
}
