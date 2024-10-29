package moe.nova.playground.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import moe.nova.bean.TimeHolder;
import moe.nova.util.JacksonObjectMapperFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class JacksonTest {

    record TestRecord(String aField, String bField) {
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = JacksonObjectMapperFactory.createJsonMapper();

        Optional<String> stringOptional = Optional.of("hello");
        var a = objectMapper.writeValueAsString(stringOptional);
        var b = objectMapper.writeValueAsString(Optional.empty());
        var c = objectMapper.readValue(a, new TypeReference<Optional<String>>() {
        });
        var d = objectMapper.readValue(b, new TypeReference<Optional<String>>() {
        });

        printSeparateLine();
        var t1 = new TestRecord("t1", "t2");
        var st1 = objectMapper.writeValueAsString(t1);
        var t2 = objectMapper.readValue(st1, TestRecord.class);

        TimeHolder clazz = new TimeHolder();
        clazz.setLocalDate(LocalDate.now());
        clazz.setLocalDateTime(LocalDateTime.now());

        String jsonStr = objectMapper.writeValueAsString(clazz);
        System.out.println("1: " + jsonStr);
        TimeHolder fromJson = objectMapper.readValue(jsonStr, TimeHolder.class);
        System.out.println("2: " + fromJson);

        String jsonStr2 = "{\"localDate\":\"2024-01-26\",\"localDateTime\":\"\", \"extra\":\"xx\"}";
        TimeHolder fromJson2 = objectMapper.readValue(jsonStr2, TimeHolder.class);
        System.out.println("3: " + fromJson2);

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("localDateTime", System.currentTimeMillis());
        TimeHolder fromJson3 = objectMapper.readValue(jsonObj.toString(), TimeHolder.class);
        System.out.println("4: " + fromJson3);

        System.out.println("=====================================================================");

        DataHolder dataHolder = new DataHolder();
        DataHolder.InnerClazz innerClazz = new DataHolder.InnerClazz();
        innerClazz.setFieldA("aaa");
        innerClazz.setFieldB("bbb");
        dataHolder.setField("ccc");
        dataHolder.setInnerClazz(innerClazz);
        String jsonStr3 = objectMapper.writeValueAsString(dataHolder);
        System.out.println("5: " + jsonStr3);
        DataHolder fromJson4 = objectMapper.readValue(jsonStr3, DataHolder.class);
        System.out.println("6: " + fromJson4);
        String jsonStr4 = "{\"field\":\"ccc\",\"innerClazz\":\"\"}";
        DataHolder fromJson5 = objectMapper.readValue(jsonStr4, DataHolder.class);
        System.out.println("7: " + fromJson5);
    }
}
