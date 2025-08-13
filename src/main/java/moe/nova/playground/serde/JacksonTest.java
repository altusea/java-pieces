package moe.nova.playground.serde;

import com.google.gson.JsonObject;
import moe.nova.bean.TimeHolder;
import moe.nova.util.JacksonObjectMapperFactory;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class JacksonTest {

    record TestRecord(String aField, String bField) {
    }

    record EnumHolder(TestEnum e) {
    }

    static void main() {
        JsonMapper jsonMapper = JacksonObjectMapperFactory.createJsonMapper();

        var n = new EnumHolder(TestEnum.INIT);
        var s = jsonMapper.writeValueAsString(n);
        IO.println(s);
        var n2 = jsonMapper.readValue(s, EnumHolder.class);
        IO.println(n2);

        IO.println("=====================================================================");

        Optional<String> stringOptional = Optional.of("hello");
        var a = jsonMapper.writeValueAsString(stringOptional);
        var b = jsonMapper.writeValueAsString(Optional.empty());
        var c = jsonMapper.readValue(a, new TypeReference<Optional<String>>() {
        });
        var d = jsonMapper.readValue(b, new TypeReference<Optional<String>>() {
        });

        printSeparateLine();
        var t1 = new TestRecord("t1", "t2");
        var st1 = jsonMapper.writeValueAsString(t1);
        var t2 = jsonMapper.readValue(st1, TestRecord.class);

        TimeHolder clazz = new TimeHolder();
        clazz.setLocalDate(LocalDate.now());
        clazz.setLocalDateTime(LocalDateTime.now());

        String jsonStr = jsonMapper.writeValueAsString(clazz);
        IO.println("1: " + jsonStr);
        TimeHolder fromJson = jsonMapper.readValue(jsonStr, TimeHolder.class);
        IO.println("2: " + fromJson);

        String jsonStr2 = "{\"localDate\":\"2024-01-26\",\"localDateTime\":\"\", \"extra\":\"xx\"}";
        TimeHolder fromJson2 = jsonMapper.readValue(jsonStr2, TimeHolder.class);
        IO.println("3: " + fromJson2);

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("localDateTime", System.currentTimeMillis());
        TimeHolder fromJson3 = jsonMapper.readValue(jsonObj.toString(), TimeHolder.class);
        IO.println("4: " + fromJson3);

        IO.println("=====================================================================");

        DataHolder dataHolder = new DataHolder();
        DataHolder.InnerClazz innerClazz = new DataHolder.InnerClazz();
        innerClazz.setFieldA("aaa");
        innerClazz.setFieldB("bbb");
        dataHolder.setField("ccc");
        dataHolder.setInnerClazz(innerClazz);
        String jsonStr3 = jsonMapper.writeValueAsString(dataHolder);
        IO.println("5: " + jsonStr3);
        DataHolder fromJson4 = jsonMapper.readValue(jsonStr3, DataHolder.class);
        IO.println("6: " + fromJson4);
        String jsonStr4 = "{\"field\":\"ccc\",\"innerClazz\":\"\"}";
        DataHolder fromJson5 = jsonMapper.readValue(jsonStr4, DataHolder.class);
        IO.println("7: " + fromJson5);
    }
}
