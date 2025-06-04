package dev.wp.craftoria_core;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Craftoria.MOD_ID)
public class Craftoria {
    public static final String MOD_ID = "craftoria_core";
    public static final String NAME = "Craftoria Core";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public Craftoria() {
    }

    public static ResourceLocation id(@NotNull String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
