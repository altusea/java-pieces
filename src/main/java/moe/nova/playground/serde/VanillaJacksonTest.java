package moe.nova.playground.serde;

import com.google.gson.JsonObject;
import moe.nova.bean.TimeHolder;
import moe.nova.util.JacksonObjectMapperFactory;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class VanillaJacksonTest {

    static void main() {
        final var defaultJsonMapper = new JsonMapper();
        final var now = LocalDateTime.now();
        final var s = defaultJsonMapper.writeValueAsString(now);
        IO.println("[line 19] " + s);
        final var t = defaultJsonMapper.readValue(s, LocalDateTime.class);
        IO.println("[line 21] " + t);

        var o = Optional.of("hello");
        IO.println("[line 24] " + defaultJsonMapper.writeValueAsString(o));
        var u = UUID.randomUUID();
        IO.println("[line 26] " + defaultJsonMapper.writeValueAsString(u));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("localDateTime", "2024-01-19T18:18:00");

        TimeHolder timeHolder = defaultJsonMapper.readValue(jsonObject.toString(), TimeHolder.class);
        IO.println("[line 32] " + timeHolder.getLocalDateTime());

        TimeHolder timeHolder1 = new TimeHolder();
        timeHolder1.setLocalDateTime(LocalDateTime.now());
        String jsonStr = defaultJsonMapper.writeValueAsString(timeHolder1);
        IO.println("[line 37] " + jsonStr);
        JsonNode jsonNode = defaultJsonMapper.readTree(jsonStr);
        IO.println("[line 39] " + jsonNode.get("localDateTime").getNodeType());

        JsonMapper jsonMapper = JacksonObjectMapperFactory.createJsonMapper();
        String jsonStr1 = jsonMapper.writeValueAsString(timeHolder1);
        IO.println("[line 43] " + jsonStr1);
        JsonNode jsonNode1 = jsonMapper.readTree(jsonStr1);
        IO.println("[line 45] " + jsonNode1.get("localDateTime").getNodeType());
    }
}
