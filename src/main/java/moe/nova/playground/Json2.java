package moe.nova.playground;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
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
                .build();
        System.out.println();
        var jsonNode = mapper.readTree(a);
        System.out.println(jsonNode);
    }
}
