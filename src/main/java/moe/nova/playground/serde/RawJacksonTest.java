package moe.nova.playground.serde;

import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class RawJacksonTest {

    public static void main(String[] args) {
        final var mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .build();
        final var now = LocalDateTime.now();
        final var s = mapper.writeValueAsString(now);
        System.out.println(s);
        final var t = mapper.readValue(s, LocalDateTime.class);
        System.out.println(t);
    }
}
