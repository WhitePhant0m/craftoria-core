package dev.wp.craftoria_core.config;

import dev.wp.craftoria_core.Craftoria;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.ModConfigSpec;

@OnlyIn(Dist.CLIENT)
public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER;
    private static final ModConfigSpec.BooleanValue DISABLE_SEARCH_TAB_AUTOFOCUS;
    public static final ModConfigSpec SPEC;

    private static String key(String path) {
        return Craftoria.ID + ".config." + path;
    }

    static {
        BUILDER = new ModConfigSpec.Builder();
        {
            BUILDER.translation(key("mixin_toggles")).push("Mixin Toggles");
            DISABLE_SEARCH_TAB_AUTOFOCUS = BUILDER
                    .translation(key("disable_search_tab_autofocus"))
                    .comment("Disable the search tab auto-focus in the creative inventory screen.")
                    .define("disable_search_tab_autofocus", true);
            BUILDER.pop();
        }
        SPEC = BUILDER.build();
    }

    public static boolean disableSearchTabAutofocus;

    public static void init() {
        disableSearchTabAutofocus = DISABLE_SEARCH_TAB_AUTOFOCUS.get();
    }
}
