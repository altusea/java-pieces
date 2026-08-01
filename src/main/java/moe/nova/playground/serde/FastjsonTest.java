package moe.nova.playground.serde;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.google.common.collect.ImmutableMap;

public class FastjsonTest {

    static void main() {
        ImmutableMap<String, Long> map = ImmutableMap.of(
                "a", 1L,
                "aa", 10L,
                "aaa", 100L,
                "aaaa", 1000L
        );
        var a = JSON.toJSONString(map, JSONWriter.Feature.WriteClassName);
        IO.println(a);

        boolean isSafeMode = Boolean.parseBoolean(System.getProperty("fastjson2.parser.safeMode"));
        System.out.println("Fastjson2 SafeMode 是否开启: " + isSafeMode);
        JSON.parseObject(a);

        System.setProperty("fastjson2.parser.safeMode", "true");

        isSafeMode = Boolean.parseBoolean(System.getProperty("fastjson2.parser.safeMode"));
        System.out.println("Fastjson2 SafeMode 是否开启: " + isSafeMode);
        JSON.parseObject(a);
    }
}
