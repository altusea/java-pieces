package moe.nova.playground;

import moe.nova.util.ConsoleUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.StringJoiner;

public class StringTest2 {

    static void main() {
        var s1 = "a:b:c:d:e";
        System.out.println(Arrays.toString(StringUtils.split(s1, ':')));
        System.out.println(Arrays.toString(StringUtils.split(s1, ":")));
        var s2 = "a::b::c::d::e";
        System.out.println(Arrays.toString(StringUtils.split(s2, ':')));
        System.out.println(Arrays.toString(StringUtils.split(s2, ":")));
        System.out.println(Arrays.toString(StringUtils.split(s2, "::")));

        ConsoleUtil.printSeparateLine();
        StringJoiner sj = new StringJoiner("|");
        sj.add("a").add("b").add("c").add("d").add("e");
        System.out.println(sj);
    }
}
