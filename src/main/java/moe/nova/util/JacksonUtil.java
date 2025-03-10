package moe.nova.util;

import org.apache.commons.lang3.ArrayUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.type.TypeFactory;

import java.util.List;
import java.util.Map;

import static moe.nova.util.FunctionalUtil.invokeSafely;

public class JacksonUtil {

    private static final JsonMapper JSON_MAPPER;

    static {
        JSON_MAPPER = JacksonObjectMapperFactory.createJsonMapper();
    }

    public static String toJson(Object value) {
        return invokeSafely(() -> JSON_MAPPER.writeValueAsString(value));
    }

    public static <T> T fromJson(String content, Class<T> valueType) {
        return invokeSafely(() -> JSON_MAPPER.readValue(content, valueType));
    }

    public static <T> T fromJson(String content, TypeReference<T> valueTypeRef) {
        return invokeSafely(() -> JSON_MAPPER.readValue(content, valueTypeRef));
    }

    public static <T> T fromJson(String content, JavaType valueType) {
        return invokeSafely(() -> JSON_MAPPER.readValue(content, valueType));
    }

    public static JavaType buildJavaTypeLinearly(Class<?>... classes) {
        if (ArrayUtils.isEmpty(classes)) {
            return null;
        }
        TypeFactory typeFactory = JSON_MAPPER.getTypeFactory();
        int n = classes.length;
        if (n == 1) {
            return typeFactory.constructType(classes[0]);
        } else if (n == 2) {
            return typeFactory.constructParametricType(classes[0], classes[1]);
        } else {
            int cur = n - 3;
            JavaType out = typeFactory.constructParametricType(classes[cur + 1], classes[cur + 2]);
            while (cur > -1) {
                var inner = out;
                out = typeFactory.constructParametricType(classes[cur--], inner);
            }
            return out;
        }
    }

    public static void main(String[] args) {
        System.out.println(buildJavaTypeLinearly());
        System.out.println(buildJavaTypeLinearly(String.class));
        System.out.println(buildJavaTypeLinearly(List.class, Integer.class));
        System.out.println(buildJavaTypeLinearly(List.class, List.class, String.class));

        TypeFactory typeFactory = JSON_MAPPER.getTypeFactory();
        System.out.println(typeFactory.constructMapType(Map.class, String.class, Integer.class));
    }
}
