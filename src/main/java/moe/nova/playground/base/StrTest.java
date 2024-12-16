package moe.nova.playground.base;

import org.dromara.hutool.core.text.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class StrTest {

    public static void main(String[] args) {
        printSeparateLine();
        String a = "hello about 中国 西方 二分 earth all.";
        System.out.println("raw length: " + a.length());
        System.out.println("hutool length: " + StrUtil.length(a));
        System.out.println(StrUtil.replaceByCodePoint(a, 12, 20, "r"));

        String link = "https://test.regex.com/markStart/water/123456-ff3fd979581267a4";
        Pattern pattern = Pattern.compile("markStart/(\\w+)/(\\d+)-([0-9a-f]{16})$");
        Matcher matcher = pattern.matcher(link);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        } else {
            System.out.println("not matched!");
        }

        final String MSG = "{\"encrypt\":\"%1$s\",\"msg_signature\":\"%2$s\",\"timestamp\":\"%3$s\",\"nonce\":\"%4$s\"}";
        System.out.printf(MSG + "%n", 1, 2, "xx3", 4);
    }
}
