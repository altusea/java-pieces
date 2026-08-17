package moe.nova.playground.serde;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Fastjson1Test {

    static void main() {
        var b = new DataHolder();
        b.setField("hello");
        JSONObject jsonObj = (JSONObject) JSON.toJSON(b);
        System.out.println(jsonObj.get("field"));
    }
}
