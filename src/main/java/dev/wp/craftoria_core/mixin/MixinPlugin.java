package dev.wp.craftoria_core.mixin;

import dev.wp.craftoria_core.util.Utils;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
    private final Map<String, Boolean> modStatus = new HashMap<>();
    private final Map<String, String> mixinToMod = new HashMap<>();

    private static final String BASE_PACKAGE = "dev.wp.craftoria_core.mixin.";

    private void setMixinToMod(String mixin, String mod) {
        mixinToMod.put(BASE_PACKAGE + mixin, mod);
    }

    @Override
    public void onLoad(String s) {
        List<String> mods = Utils.getModList();

        boolean ae2AndEmi = mods.contains("ae2") && mods.contains("emi");
        modStatus.put("ae2", ae2AndEmi);
        modStatus.put("emi", ae2AndEmi);
        modStatus.put("mynethersdelight", mods.contains("mynethersdelight"));
        modStatus.put("cable_facades", mods.contains("cable_facades"));
        modStatus.put("xycraft_core", mods.contains("xycraft_core"));
        modStatus.put("cataclysm", mods.contains("cataclysm"));
        modStatus.put("jdt", mods.contains("justdirethings"));

        setMixinToMod("ae2.KeySortersMixin", "ae2");
        setMixinToMod("emi.ReloadWorkerMixin", "emi");
        setMixinToMod("mynethersdelight.CommonEventMixin", "mynethersdelight");
        setMixinToMod("cable_facades.ServerInGameEventsMixin", "cable_facades");
        setMixinToMod("xycraft.XyCoreClientMixin", "xycraft_core");
        setMixinToMod("cataclysm.CursedTombstoneEntityMixin", "cataclysm");
        setMixinToMod("jdt.CreatureCatcherEntityMixin", "jdt");
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String modId = mixinToMod.get(mixinClassName);
        return modId == null || modStatus.getOrDefault(modId, false);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
