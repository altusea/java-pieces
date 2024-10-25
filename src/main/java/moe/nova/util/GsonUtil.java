package moe.nova.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import moe.nova.util.internal.gson.*;

import java.util.List;
import java.util.Optional;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class GsonUtil {

    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapterFactory(DateTypeAdapter.FACTORY)
                .registerTypeAdapterFactory(LocalDateTypeAdapter.FACTORY)
                .registerTypeAdapterFactory(LocalDateTimeTypeAdapter.FACTORY)
                .registerTypeAdapterFactory(YearMonthTypeAdapter.FACTORY)
                .registerTypeAdapterFactory(OptionalTypeAdapter.factory());
        gson = builder.create();
    }

    public static <T> T fromJson(String jsonStr, Class<T> classOfT) {
        return gson.fromJson(jsonStr, classOfT);
    }

    public static <T> T fromJson(String jsonStr, TypeToken<T> typeOfT) {
        return gson.fromJson(jsonStr, typeOfT);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static void main(String[] args) {
        Optional<String> a = Optional.of("a");
        Optional<String> b = Optional.empty();
        Optional<List<String>> c = Optional.of(List.of("a", "b", "c"));
        Optional<String> d = null;
        System.out.println(GsonUtil.toJson(a));
        System.out.println(GsonUtil.toJson(b));
        System.out.println(GsonUtil.toJson(c));
        System.out.println(GsonUtil.toJson(d));

        printSeparateLine();
        System.out.println(GsonUtil.fromJson("[\"a\"]", new TypeToken<Optional<String>>() {
        }));
        System.out.println(GsonUtil.fromJson("[]", new TypeToken<Optional<String>>() {
        }));
        System.out.println(GsonUtil.fromJson("[[\"a\",\"b\",\"c\"]]", new TypeToken<Optional<List<String>>>() {
        }));
        System.out.println(GsonUtil.fromJson("null", new TypeToken<Optional<String>>() {
        }));

    }
}
