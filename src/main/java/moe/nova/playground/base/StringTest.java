package moe.nova.playground.base;

import kala.text.StringSlice;
import moe.nova.util.StringUtil;

public class StringTest {

    public static void main(String[] args) {
        String s = """
                第一行
                第二行
                第三行
                """;
        IO.println(s);
        IO.println("===========");
        String onlyAscii = "hello, world";
        String hybrid = "我能吞下glass而不伤害body";
        IO.println(onlyAscii.length()); // should be 12
        IO.println(hybrid.length()); // should be 17
        int[] codePoints = StringUtil.getCodePoints(hybrid);
        IO.println(codePoints.length); // should be 17
        IO.println("能".equals(Character.toString(codePoints[1]))); // should be true

        IO.println(onlyAscii.regionMatches(true, 0, "Hello, World", 0, onlyAscii.length()));

        var sl = StringSlice.of("hello, world");
        IO.println(sl.isEmpty());
        IO.println(sl.contentEquals("hello"));
    }
}
