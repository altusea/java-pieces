package moe.nova.util;

import java.util.function.Supplier;

public interface LazyValue<T> extends Supplier<T> {

    /**
     * Create a lazy value from a supplier.
     *
     * @param supplier supplier to get the value from
     * @param <T>      type of the value
     * @return a lazy value that will obtain the value from supplier on first call to {@link #get()}
     */
    static <T> LazyValue<T> create(Supplier<T> supplier) {
        return new LazyValueImpl<>(supplier);
    }

    /**
     * Create a lazy value from a value.
     *
     * @param value actual value to return
     * @param <T>   type of the value
     * @return a lazy value that will always return the value provided
     */
    static <T> LazyValue<T> create(T value) {
        return new LazyValueImpl<>(value);
    }

    /**
     * Return true if the value is loaded, false if the supplier was not invoked.
     *
     * @return {@code true} if the value is loaded
     */
    boolean isLoaded();

}
