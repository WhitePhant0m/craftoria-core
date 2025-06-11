package dev.wp.craftoria_core.mixin.generic;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.wp.craftoria_core.Craftoria;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings({"rawtypes"})
@Debug(export = true)
@Mixin(StateHolder.class)
public abstract class CommonlyMissingStateFixes<S, O> {
    @Shadow public abstract <T extends Comparable<T>, V extends T> S trySetValue(Property<T> property, V value);

    @Shadow public abstract <T extends Comparable<T>> boolean hasProperty(Property<T> property);

    @Unique
    private static final HashMap<Property, Comparable> CraftoriaFixes$defaultMap = new HashMap<>();
    @ModifyExpressionValue(method="getValue", at= @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Reference2ObjectArrayMap;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object getDefaultValueIfMissing(Object value, Property property) {
        if (value == null) {
            if (!CraftoriaFixes$defaultMap.containsKey(property)) {
                Craftoria.LOGGER.warn("Prevented crash while some mod attempted to lookup missing blockstate property {} in state {}", property, this);
            }
            return CraftoriaFixes$defaultMap.computeIfAbsent(property, blockProperty -> {
                Iterator iterator = blockProperty.getPossibleValues().iterator();
                if (iterator.hasNext()) {
                    Object obj = iterator.next();
                    if (obj instanceof Comparable comparableCast) {
                        Craftoria.LOGGER.warn("Providing default value {} for property {}", comparableCast, property);
                        return comparableCast;
                    }
                    else {
                        Craftoria.LOGGER.warn("BlockProperty value must implement Comparable! {}, {}", property, obj.toString());
                        return null;
                    }
                }else{
                    Craftoria.LOGGER.warn("Attempted to lookup blockstate property {} default without valid values {}", property, property.getPossibleValues());
                }
                return null;
            });
        }
        return value;
    }

    @WrapMethod(method="setValue")
    private <T extends Comparable<T>, V extends T> S fastFailSetIfMissing(Property<T> property, V value, Operation<S> original) {
        try {
            if (this.hasProperty(property)) return original.call(property, value);
        } catch (Exception e) {
            Craftoria.LOGGER.debug(e.getMessage(), e);
        }
        return this.trySetValue(property, value);
    }
}
