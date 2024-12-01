package moe.nova.util;

import org.jspecify.annotations.NonNull;

import java.util.Comparator;

public class ComparatorUtil {

    public static <T extends Comparable<T>> boolean gt(T a, T b) {
        return a.compareTo(b) > 0;
    }

    public static <T> boolean gt(T a, T b, @NonNull Comparator<T> comparator) {
        return comparator.compare(a, b) > 0;
    }

    public static <T extends Comparable<T>> boolean geq(T a, T b) {
        return a.compareTo(b) >= 0;
    }

    public static <T> boolean geq(T a, T b, @NonNull Comparator<T> comparator) {
        return comparator.compare(a, b) >= 0;
    }

    public static <T extends Comparable<T>> boolean lt(T a, T b) {
        return a.compareTo(b) < 0;
    }

    public static <T> boolean lt(T a, T b, @NonNull Comparator<T> comparator) {
        return comparator.compare(a, b) < 0;
    }

    public static <T extends Comparable<T>> boolean leq(T a, T b) {
        return a.compareTo(b) <= 0;
    }

    public static <T> boolean leq(T a, T b, @NonNull Comparator<T> comparator) {
        return comparator.compare(a, b) <= 0;
    }

    public static <T extends Comparable<T>> boolean eq(T a, T b) {
        return a.compareTo(b) == 0;
    }

    public static <T> boolean eq(T a, T b, @NonNull Comparator<T> comparator) {
        return comparator.compare(a, b) == 0;
    }
}
