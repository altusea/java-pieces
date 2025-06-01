package moe.nova.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtil {

    public static String removeWhitespaces(final String input) {
        return Arrays.stream(input.split("\n")).map(String::trim).collect(Collectors.joining(" "));
    }

    public static int[] getCodePoints(final String input) {
        if (input == null || input.isEmpty()) return new int[0];
        int length = input.length();
        int[] res = new int[length];
        for (int offset = 0, i = 0; offset < length; ) {
            int ch = input.codePointAt(offset);
            res[i++] = ch;
            offset += Character.charCount(ch);
        }
        return res;
    }

    static void main(String[] args) {
        String hybrid = "我能吞下glass而不伤害body";
        int[] codePoints = StringUtils.toCodePoints(hybrid);
        for (int codePoint : codePoints) {
            IO.println(Character.toString(codePoint));
        }
    }
}
