package moe.nova.util;

import java.time.Duration;

public class NumericUtil {

    private NumericUtil() {
    }

    /**
     * Returns the {@code int} nearest in value to {@code value}.
     *
     * @param value any {@code long} value
     * @return the same value cast to {@code int} if it is in the range of the {@code int} type,
     * {@link Integer#MAX_VALUE} if it is too large, or {@link Integer#MIN_VALUE} if it is too
     * small
     */
    public static int saturatedCast(long value) {
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

    public static Duration min(Duration a, Duration b) {
        return (a.compareTo(b) < 0) ? a : b;
    }

    public static Duration max(Duration a, Duration b) {
        return (a.compareTo(b) > 0) ? a : b;
    }
}
