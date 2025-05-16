package moe.nova.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import moe.nova.util.internal.gson.*;

import java.util.List;
import java.util.Optional;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class GsonUtil {

    private static final StableValue<Gson> GSON = StableValue.of();

    static Gson getGsonInstance() {
        return GSON.orElseSet(() -> {
            GsonBuilder builder = new GsonBuilder()
                    .registerTypeAdapterFactory(DateTypeAdapter.FACTORY)
                    .registerTypeAdapterFactory(LocalDateTypeAdapter.FACTORY)
                    .registerTypeAdapterFactory(LocalDateTimeTypeAdapter.FACTORY)
                    .registerTypeAdapterFactory(YearMonthTypeAdapter.FACTORY)
                    .registerTypeAdapterFactory(OptionalTypeAdapter.factory());
            return builder.create();
        });
    }

    public static <T> T fromJson(String jsonStr, Class<T> classOfT) {
        return getGsonInstance().fromJson(jsonStr, classOfT);
    }

    public static <T> T fromJson(String jsonStr, TypeToken<T> typeOfT) {
        return getGsonInstance().fromJson(jsonStr, typeOfT);
    }

    public static String toJson(Object obj) {
        return getGsonInstance().toJson(obj);
    }

    public static void main(String[] args) {
        Optional<String> a = Optional.of("a");
        Optional<String> b = Optional.empty();
        Optional<List<String>> c = Optional.of(List.of("a", "b", "c"));
        System.out.println(GsonUtil.toJson(a));
        System.out.println(GsonUtil.toJson(b));
        System.out.println(GsonUtil.toJson(c));

        printSeparateLine();
        System.out.println(GsonUtil.fromJson("[\"a\"]", new TypeToken<Optional<String>>() {
        }));
        System.out.println(GsonUtil.fromJson("[]", new TypeToken<Optional<String>>() {
        }));
        System.out.println(GsonUtil.fromJson("[[\"a\",\"b\",\"c\"]]", new TypeToken<Optional<List<String>>>() {
        }));
    }
}
