package dev.wp.craftoria_core;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Mod(Craftoria.MOD_ID)
public class Craftoria {
    public static final String MOD_ID = "craftoria_core";
    public static final String NAME = "Craftoria Core";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public Craftoria() {
    }

    public static List<String> getModList() {
        return LoadingModList.get().getMods().stream().map(ModInfo::getModId).toList();
    }
}
