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

    static final String NULL_STR = "null";

    static final String NON_JSON_STR = "abc";

    static final String JSON_STR = "{\"desc\":\"abc\"}";

    @Test
    void testFastjson() {
        assertNull(JSON.parseObject(NULL_STR, String.class));
        assertNull(JSON.parseObject(NULL_STR, DataHolder.class));
        assertNull(JSON.parseObject((String) null, DataHolder.class));

        assertDoesNotThrow(() -> JSON.parseObject(JSON_STR, DataHolder.class));
        assertThrows(JSONException.class, () -> JSON.parseObject(NON_JSON_STR, String.class));
        assertThrows(JSONException.class, () -> JSON.parseObject(NON_JSON_STR, DataHolder.class));
    }

    @Test
    void testGson() {
        Gson gson = new Gson();
        assertNull(gson.fromJson(NULL_STR, String.class));
        assertNull(gson.fromJson(NULL_STR, DataHolder.class));
        assertNull(gson.fromJson((String) null, DataHolder.class));

        assertDoesNotThrow(() -> gson.fromJson(JSON_STR, DataHolder.class));
        assertDoesNotThrow(() -> gson.fromJson(NON_JSON_STR, String.class));
        assertThrows(JsonSyntaxException.class, () -> gson.fromJson(NON_JSON_STR, DataHolder.class));
    }

    @Test
    void testJackson() {
        JsonMapper jsonMapper = new JsonMapper();
        assertNull(jsonMapper.readValue(NULL_STR, String.class));
        assertNull(jsonMapper.readValue(NULL_STR, DataHolder.class));
        assertThrows(IllegalArgumentException.class, () -> jsonMapper.readValue((String) null, DataHolder.class));

        assertDoesNotThrow(() -> jsonMapper.readValue(JSON_STR, DataHolder.class));
        assertThrows(StreamReadException.class, () -> jsonMapper.readValue(NON_JSON_STR, String.class));
        assertThrows(StreamReadException.class, () -> jsonMapper.readValue(NON_JSON_STR, DataHolder.class));
    }
}
