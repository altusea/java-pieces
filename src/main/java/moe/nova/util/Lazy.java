package moe.nova.util;

import java.util.function.Supplier;

@FunctionalInterface
public interface Lazy<T> extends Supplier<T> {

    static <T> Lazy<T> of(Supplier<? extends T> original) {
        return StableValue.supplier(original)::get;
    }
}
