package moe.nova.playground;

import dev.toonformat.jtoon.JToon;
import moe.nova.util.JacksonUtil;

import java.util.HashMap;
import java.util.Map;

public class JToonTest {

    static void main() {
        System.out.println("Hello World!");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "john");
        map.put("age", 18);
        map.put("info", Map.of("gender", "F"));
        System.out.println(JacksonUtil.toJson(map));
        System.out.println(JToon.encode(map));
    }
}
