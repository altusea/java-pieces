package moe.nova.util;

import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NonNull;

public class ConsoleUtil {

    private static final String SEPARATE_LINE = "=".repeat(80);

    private static final String SHORTER_SEPARATE_LINE = "=".repeat(20);

    public static void printLine() {
        IO.println();
    }

    public static void printSeparateLine() {
        IO.println(SEPARATE_LINE);
    }

    public static void printSeparateLine(final @NonNull String msg) {
        var str = StringUtils.center(msg, 40);
        IO.println(SHORTER_SEPARATE_LINE + str + SHORTER_SEPARATE_LINE);
    }

    static void main() {
        IO.println(StringUtils.repeat("=", 80));
    }
}
