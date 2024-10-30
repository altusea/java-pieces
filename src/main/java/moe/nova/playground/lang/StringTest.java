package moe.nova.playground.lang;

import kala.text.StringSlice;
import moe.nova.util.StringUtil;

public class StringTest {

    public static void main(String[] args) {
        String onlyAscii = "hello, world";
        String hybrid = "我能吞下glass而不伤害body";
        System.out.println(onlyAscii.length()); // should be 12
        System.out.println(hybrid.length()); // should be 17
        int[] codePoints = StringUtil.getCodePoints(hybrid);
        System.out.println(codePoints.length); // should be 17
        System.out.println("能".equals(Character.toString(codePoints[1]))); // should be true

        System.out.println(onlyAscii.regionMatches(true, 0, "Hello, World", 0, onlyAscii.length()));

        var sl = StringSlice.of("hello, world");
        System.out.println(sl.isEmpty());
        System.out.println(sl.contentEquals("hello"));
    }
}
