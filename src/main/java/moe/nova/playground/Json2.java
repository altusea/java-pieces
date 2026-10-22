package moe.nova.playground;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

public class Json2 {

    static void main() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("b", "xxx");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("a", jsonObject);
        System.out.println(jsonObject2.toJSONString());

        String a = "{ \"a\": { \"b\": \"123\" } }";
        System.out.println(JSON.isValid(a));
    }
}
