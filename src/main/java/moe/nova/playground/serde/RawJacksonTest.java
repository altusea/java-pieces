package moe.nova.playground.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class RawJacksonTest {

    public static void main(String[] args) throws JsonProcessingException {
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
