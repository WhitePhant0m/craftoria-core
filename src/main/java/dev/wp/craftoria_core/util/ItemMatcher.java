package dev.wp.craftoria_core.util;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ItemMatcher {
    private final boolean useComponents;
    private final @NotNull Item item;
    private final @Nullable DataComponentMap components;

    public ItemMatcher(@NotNull Item item, @Nullable DataComponentMap components, boolean useComponents) {
        this.item = item;
        this.components = components;
        this.useComponents = useComponents;
    }

    public static ItemMatcher of(@NotNull Item item) {
        return new ItemMatcher(item, item.components(), true);
    }

    public static ItemMatcher ignoreNbt(@NotNull Item item) {
        return new ItemMatcher(item, null, false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemMatcher other)) return false;
        if (item != other.item) return false;
        if (!useComponents) return true;
        return Objects.equals(components, other.components);
    }

    @Override
    public int hashCode() {
        return useComponents ? Objects.hash(item, components) : item.hashCode();
    }
}