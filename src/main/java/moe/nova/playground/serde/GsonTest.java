package moe.nova.playground.serde;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import moe.nova.util.GsonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public class GsonTest {

    public static void main(String[] args) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(List.of()));
        String jsonString = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        System.out.println(jsonObject.asMap().size());

        TimeHolder clazz = new TimeHolder();
        clazz.setYearMonth(YearMonth.now());
        clazz.setLocalDate(LocalDate.now());
        clazz.setLocalDateTime(LocalDateTime.now());

        String jsonStr = GsonUtil.toJson(clazz);
        System.out.println(jsonStr);
        TimeHolder fromJson = GsonUtil.fromJson(jsonStr, TimeHolder.class);
        System.out.println(fromJson);

        String jsonStr2 = "{\"localDate\":\"2024-01-26\",\"localDateTime\":\"\"}";
        TimeHolder fromJson2 = GsonUtil.fromJson(jsonStr2, TimeHolder.class);
        System.out.println(fromJson2);

        DataHolder dataHolder = new DataHolder();
        DataHolder.InnerClazz innerClazz = new DataHolder.InnerClazz();
        innerClazz.setFieldA("aaa");
        innerClazz.setFieldB("bbb");
        dataHolder.setField("ccc");
        dataHolder.setInnerClazz(innerClazz);
        String jsonStr3 = GsonUtil.toJson(dataHolder);
        System.out.println(jsonStr3);
        DataHolder fromJson3 = GsonUtil.fromJson(jsonStr3, DataHolder.class);
        System.out.println(fromJson3);
        String jsonStr4 = "{\"field\":\"ccc\",\"innerClazz\":\"\"}";
        DataHolder fromJson4 = GsonUtil.fromJson(jsonStr4, DataHolder.class);
        System.out.println(fromJson4);
    }
}
