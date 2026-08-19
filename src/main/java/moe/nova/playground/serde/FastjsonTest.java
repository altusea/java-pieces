package moe.nova.playground.serde;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class FastjsonTest {

    static void main() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "1");
        hashMap.put("b", "2");
        System.out.println(hashMap);

        ImmutableMap<String, Long> map = ImmutableMap.of(
                "aaaa", 1L,
                "aaa", 10L,
                "aa", 100L,
                "a", 1000L
        );
        var a = JSON.toJSONString(map, JSONWriter.Feature.WriteClassName);
        System.out.println(a);
    }
}
