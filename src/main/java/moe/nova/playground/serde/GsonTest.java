package moe.nova.playground.serde;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import moe.nova.bean.TimeHolder;
import moe.nova.util.GsonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class GsonTest {

    static void main() {
        Gson gson = new Gson();
        IO.println(gson.toJson(List.of()));
        String jsonString = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        IO.println(jsonObject.asMap().size());

        TimeHolder clazz = new TimeHolder();
        clazz.setYearMonth(YearMonth.now());
        clazz.setLocalDate(LocalDate.now());
        clazz.setLocalDateTime(LocalDateTime.now());

        String jsonStr = GsonUtil.toJson(clazz);
        IO.println("[line 30] " + jsonStr);
        TimeHolder fromJson = GsonUtil.fromJson(jsonStr, TimeHolder.class);
        IO.println("[line 32] " + fromJson);

        String jsonStr2 = "{\"localDate\":\"2024-01-26\"}";
        TimeHolder fromJson2 = GsonUtil.fromJson(jsonStr2, TimeHolder.class);
        IO.println("[line 36] " + fromJson2);

        DataHolder dataHolder = new DataHolder();
        DataHolder.InnerClazz innerClazz = new DataHolder.InnerClazz();
        innerClazz.setFieldA("aaa");
        innerClazz.setFieldB("bbb");
        dataHolder.setField("ccc");
        dataHolder.setInnerClazz(innerClazz);
        String jsonStr3 = GsonUtil.toJson(dataHolder);
        IO.println("[line 45] " + jsonStr3);
        DataHolder fromJson3 = GsonUtil.fromJson(jsonStr3, DataHolder.class);
        IO.println("[line 47] " + fromJson3);

        printSeparateLine();
        String jsonStr4 = "{\"field\":\"ccc\",\"innerClazz\":\"\"}";
        DataHolder fromJson4 = GsonUtil.fromJson(jsonStr4, DataHolder.class);
        IO.println(fromJson4);
    }
}
