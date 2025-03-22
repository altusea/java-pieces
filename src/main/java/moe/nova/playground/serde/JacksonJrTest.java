package moe.nova.playground.serde;

import moe.nova.util.JacksonUtil;
import tools.jackson.jr.annotationsupport.JacksonAnnotationExtension;
import tools.jackson.jr.extension.javatime.JacksonJrJavaTimeExtension;
import tools.jackson.jr.ob.JSON;

import java.io.IOException;
import java.util.List;

public class JacksonJrTest {

    record TestRecord(int id, String name, int age) {
    }

    static class TestClazz {

        private int id;
        private String name;
        private int age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) throws IOException {
        var a = new TestRecord(1, "A", 18);
        System.out.println(JSON.std.asString(a));
        System.out.println(JacksonUtil.toJson(a));

        var b = new TestClazz();
        b.setId(1);
        b.setName("B");
        System.out.println(JSON.std.asString(b));

        List<String> c = List.of("a", "bb", "ccc");
        var cs = JSON.std.asString(c);
        List<String> d = JSON.std.listOfFrom(String.class, cs);
        System.out.println(d);
        System.out.println("================================");
        var jsonExt = JSON.builder()
                .register(JacksonAnnotationExtension.std)
                .register(new JacksonJrJavaTimeExtension())
                .build();
        var json = jsonExt.asString(a);
        System.out.println(json);
    }
}
