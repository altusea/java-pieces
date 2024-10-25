package moe.nova.util;

import java.util.stream.Stream;

public final class ComparableUtil {
    private ComparableUtil() {
    }

    /**
     * Does a safe comparison of two {@link Comparable} objects accounting for nulls
     *
     * @param d1 First object
     * @param d2 Second object
     * @return A positive number if the object double is larger, a negative number if the second object is larger, or 0
     * if they are equal. Null is considered less than any non-null value
     */
    public static <T> int safeCompare(Comparable<T> d1, T d2) {
        if (d1 != null && d2 != null) {
            return d1.compareTo(d2);
        } else if (d1 == null && d2 != null) {
            return -1;
        } else if (d1 != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get the minimum value from a list of comparable vales.
     *
     * @param values The values from which the minimum should be extracted.
     * @return The minimum value in the list.
     */
    @SafeVarargs
    public static <T extends Comparable<T>> T minimum(T... values) {
        return values == null ? null : Stream.of(values).min(Comparable::compareTo).orElse(null);
    }

    /**
     * Get the maximum value from a list of comparable vales.
     *
     * @param values The values from which the maximum should be extracted.
     * @return The maximum value in the list.
     */
    @SafeVarargs
    public static <T extends Comparable<T>> T maximum(T... values) {
        return values == null ? null : Stream.of(values).max(Comparable::compareTo).orElse(null);
    }

}
