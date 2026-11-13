package moe.nova.playground;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import it.unimi.dsi.fastutil.longs.LongList;
import moe.nova.util.ConsoleUtil;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.datatype.fastutil.FastutilModule;
import tools.jackson.module.blackbird.BlackbirdModule;

public class Json2 {

    static void main() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("b", "123");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("a", jsonObject);
        System.out.println(jsonObject2.toJSONString());

        String a = "{ \"a\": { \"b\": \"456\" } }";
        System.out.println(JSON.isValid(a));

        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new BlackbirdModule())
                .addModule(new FastutilModule())
                .build();
        System.out.println();
        var jsonNode = mapper.readTree(a);
        System.out.println(jsonNode);

        ConsoleUtil.printSeparateLine();
        LongList longList = LongList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        var b = mapper.writeValueAsString(longList);
        System.out.println(b);
        var c = mapper.readValue(b, LongList.class);
        System.out.println(c.getClass());
    }
}
