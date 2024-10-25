package moe.nova.playground.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.JsonObject;
import moe.nova.util.JacksonObjectMapperFactory;

import java.time.LocalDateTime;

public class DefaultTimeModuleTest {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.registerModule(new JavaTimeModule());

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("localDateTime", "2024-01-19T18:18:00");

        TimeHolder timeHolder = defaultObjectMapper.readValue(jsonObject.toString(), TimeHolder.class);
        System.out.println(timeHolder.getLocalDateTime());

        TimeHolder timeHolder1 = new TimeHolder();
        timeHolder1.setLocalDateTime(LocalDateTime.now());
        String jsonStr = defaultObjectMapper.writeValueAsString(timeHolder1);
        System.out.println("[0] " + jsonStr);
        JsonNode jsonNode = defaultObjectMapper.readTree(jsonStr);
        System.out.println(jsonNode.get("localDateTime").getNodeType());

        ObjectMapper objectMapper = JacksonObjectMapperFactory.createJsonMapper();
        String jsonStr1 = objectMapper.writeValueAsString(timeHolder1);
        System.out.println("[1] " + jsonStr1);
        JsonNode jsonNode1 = objectMapper.readTree(jsonStr1);
        System.out.println(jsonNode1.get("localDateTime").getNodeType());
    }
}
