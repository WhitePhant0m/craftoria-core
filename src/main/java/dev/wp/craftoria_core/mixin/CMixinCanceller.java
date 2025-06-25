package dev.wp.craftoria_core.mixin;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

public class CMixinCanceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return mixinClassName.equals("cn.dancingsnow.bigger_ae2.mixins.CraftingCPUClusterMixin");
    }
}
