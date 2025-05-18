package moe.nova.playground.generic;

import moe.nova.util.JacksonObjectMapperFactory;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.type.TypeFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaTypeTest {

    public static void main(String[] args) {
        TypeFactory typeFactory1 = TypeFactory.createDefaultInstance();
        TypeFactory typeFactory2 = JacksonObjectMapperFactory.createJsonMapper().getTypeFactory();
        IO.println("typeFactory1 == typeFactory2: " + (typeFactory1 == typeFactory2));

        JavaType javaType = typeFactory1.constructParametricType(List.class, String.class);
        IO.println(javaType);

        JavaType javaType2 = typeFactory1.constructParametricType(Map.class, String.class, Integer.class);
        IO.println(javaType2);

        JavaType inner = typeFactory1.constructParametricType(Set.class, Integer.class);
        JavaType javaType3 = typeFactory1.constructParametricType(List.class, inner);
        IO.println(javaType3);
    }
}
