package moe.nova.playground.serde;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import moe.nova.bean.TimeHolder;
import moe.nova.util.GsonUtil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class GsonTest {

    static void main() {
        Gson gson = new Gson();
        IO.println("[line 20] " + gson.toJson(List.of()));
        String jsonString = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        IO.println("[line 23] " + jsonObject.asMap().size());

        TimeHolder clazz = new TimeHolder();
        clazz.setYearMonth(YearMonth.now());
        clazz.setLocalDate(LocalDate.now());
        clazz.setLocalDateTime(LocalDateTime.now());

        String jsonStr = GsonUtil.toJson(clazz);
        IO.println("[line 31] " + jsonStr);
        TimeHolder fromJson = GsonUtil.fromJson(jsonStr, TimeHolder.class);
        IO.println("[line 33] " + fromJson);

        String jsonStr2 = "{\"localDate\":\"2024-01-26\"}";
        TimeHolder fromJson2 = GsonUtil.fromJson(jsonStr2, TimeHolder.class);
        IO.println("[line 37] " + fromJson2);

        DataHolder dataHolder = new DataHolder();
        DataHolder.InnerClazz innerClazz = new DataHolder.InnerClazz();
        innerClazz.setFieldA("aaa");
        innerClazz.setFieldB("bbb");
        dataHolder.setField("ccc");
        dataHolder.setInnerClazz(innerClazz);
        String jsonStr3 = GsonUtil.toJson(dataHolder);
        IO.println("[line 46] " + jsonStr3);
        DataHolder fromJson3 = GsonUtil.fromJson(jsonStr3, DataHolder.class);
        IO.println("[line 48] " + fromJson3);

        printSeparateLine();
        var s = gson.fromJson("abc", String.class);
        IO.println("[line 52] " + s.getClass());
        IO.println("[line 53] " + s);
        var s1 = GsonUtil.fromJson("abc", String.class);
        IO.println("[line 55] " + s1.getClass());
        IO.println("[line 56] " + s1);

        printSeparateLine();
        var t1 = Instant.now();
        IO.println("[line 60] " + gson.toJson(t1));
    }
}
