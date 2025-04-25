package dev.wp.craftoria_core.mixin;

import com.google.common.collect.ImmutableMap;
import dev.wp.craftoria_core.util.Utils;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class MixinPlugin implements IMixinConfigPlugin {
    private static final Supplier<Boolean> TRUE = () -> true;

    private static final Map<String, Supplier<Boolean>> COND = ImmutableMap.of(
            "dev.wp.craftoria_core.mixin.ae2.KeySortersMixin", () -> Utils.isModLoaded("ae2"),
            "dev.wp.craftoria_core.mixin.emi.ReloadWorkerMixin", () -> Utils.isModLoaded("emi"),
            "dev.wp.craftoria_core.mixin.excavein.BlockOutlineRenderedMixin", () -> Utils.excaveinLoaded,
            "dev.wp.craftoria_core.mixin.excavein.ServerConfigMixin", () -> Utils.excaveinLoaded,
            "dev.wp.craftoria_core.mixin.excavein.ServerPlayerGameModeMixinSquared", () -> Utils.excaveinLoaded,
            "dev.wp.craftoria_core.mixin.mob_grinding_utils.TileEntitySawMixin", () -> Utils.isModLoaded("mob_grinding_utils"),
            "dev.wp.craftoria_core.mixin.mynethersdelight.CommonEventMixin", () -> Utils.isModLoaded("mynethersdelight"),
            "dev.wp.craftoria_core.mixin.skillsmod.SkillsAPIMixin", () -> Utils.isModLoaded("puffish_skills")
    );

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return COND.getOrDefault(targetClassName, TRUE).get();
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
