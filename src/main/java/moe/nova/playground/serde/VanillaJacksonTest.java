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

    public static void main(String[] args) {
        final var defaultJsonMapper = JsonMapper.builder().build();
        final var now = LocalDateTime.now();
        final var s = defaultJsonMapper.writeValueAsString(now);
        System.out.println(s);
        final var t = defaultJsonMapper.readValue(s, LocalDateTime.class);
        System.out.println(t);

        var o = Optional.of("hello");
        System.out.println(defaultJsonMapper.writeValueAsString(o));
        var u = UUID.randomUUID();
        System.out.println(defaultJsonMapper.writeValueAsString(u));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("localDateTime", "2024-01-19T18:18:00");

        TimeHolder timeHolder = defaultJsonMapper.readValue(jsonObject.toString(), TimeHolder.class);
        System.out.println("[0] " + timeHolder.getLocalDateTime());

        TimeHolder timeHolder1 = new TimeHolder();
        timeHolder1.setLocalDateTime(LocalDateTime.now());
        String jsonStr = defaultJsonMapper.writeValueAsString(timeHolder1);
        System.out.println("[1] " + jsonStr);
        JsonNode jsonNode = defaultJsonMapper.readTree(jsonStr);
        System.out.println("[2] " + jsonNode.get("localDateTime").getNodeType());

        JsonMapper jsonMapper = JacksonObjectMapperFactory.createJsonMapper();
        String jsonStr1 = jsonMapper.writeValueAsString(timeHolder1);
        System.out.println("[3] " + jsonStr1);
        JsonNode jsonNode1 = jsonMapper.readTree(jsonStr1);
        System.out.println("[4] " + jsonNode1.get("localDateTime").getNodeType());
    }
}
