package moe.nova.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Test;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.*;

public class JsonSerdeTest {

    static final String NON_JSON_STR = "abc";

    static final String JSON_STR = "{\"desc\":\"abc\"}";

    @Test
    void testFastjson() {
        assertNull(JSON.parseObject("null", String.class));
        assertNull(JSON.parseObject("null", DataHolder.class));
        assertNull(JSON.parseObject((String) null, DataHolder.class));

        assertDoesNotThrow(() -> JSON.parseObject(JSON_STR, DataHolder.class));
        assertThrows(JSONException.class, () -> JSON.parseObject(NON_JSON_STR, DataHolder.class));
    }

    @Test
    void testGson() {
        Gson gson = new Gson();
        assertNull(gson.fromJson("null", String.class));
        assertNull(gson.fromJson("null", DataHolder.class));
        assertNull(gson.fromJson((String) null, DataHolder.class));

        assertDoesNotThrow(() -> gson.fromJson(JSON_STR, DataHolder.class));
        assertThrows(JsonSyntaxException.class, () -> gson.fromJson(NON_JSON_STR, DataHolder.class));
    }

    @Test
    void testJackson() {
        JsonMapper jsonMapper = new JsonMapper();
        assertNull(jsonMapper.readValue("null", String.class));
        assertNull(jsonMapper.readValue("null", DataHolder.class));
        assertThrows(IllegalArgumentException.class, () -> jsonMapper.readValue((String) null, DataHolder.class));

        assertDoesNotThrow(() -> jsonMapper.readValue(JSON_STR, DataHolder.class));
        assertThrows(StreamReadException.class, () -> jsonMapper.readValue(NON_JSON_STR, DataHolder.class));
    }
}
