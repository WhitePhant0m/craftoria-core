package dev.wp.craftoria_core.ae2.item.cell;

import appeng.api.stacks.AEKey;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.util.Objects;

// Taken from BiggerAE2(MIT), credits to the original author, DancingSnow0517.
public class BlackHoleStorage {
    public static final Codec<BlackHoleStorage> CODEC =
            RecordCodecBuilder.create(instance -> instance
                    .group(
                            AEKey.CODEC.fieldOf("key").forGetter(BlackHoleStorage::getStoredItem),
                            Codec.STRING
                                    .flatXmap(
                                            it -> {
                                                try {
                                                    return DataResult.success(new BigInteger(it));
                                                } catch (NumberFormatException e) {
                                                    return DataResult.success(BigInteger.ZERO);
                                                }
                                            },
                                            it -> DataResult.success(it.toString()))
                                    .fieldOf("count")
                                    .forGetter(BlackHoleStorage::getCount))
                    .apply(instance, BlackHoleStorage::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BlackHoleStorage> STREAM_CODEC =
            StreamCodec.ofMember(BlackHoleStorage::encode, BlackHoleStorage::new);

    @Nullable
    private final AEKey storedItem;

    private final BigInteger count;

    public BlackHoleStorage(@Nullable AEKey storedItem, BigInteger count) {
        this.storedItem = storedItem;
        this.count = count;
    }

    public BlackHoleStorage(RegistryFriendlyByteBuf buf) {
        storedItem = AEKey.readOptionalKey(buf);
        String integerStr = buf.readUtf();
        BigInteger c;
        try {
            c = new BigInteger(integerStr);
        } catch (NumberFormatException e) {
            c = BigInteger.ZERO;
        }
        count = c;
    }

    @Nullable
    public AEKey getStoredItem() {
        return storedItem;
    }

    public BigInteger getCount() {
        return count;
    }

    public void encode(RegistryFriendlyByteBuf buf) {
        AEKey.writeOptionalKey(buf, storedItem);
        buf.writeUtf(count.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof BlackHoleStorage storage) {
            return Objects.equals(storage.getStoredItem(), getStoredItem())
                    && Objects.equals(storage.getCount(), getCount());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoredItem(), getCount());
    }
}