package moe.nova.playground.serde;

import com.fasterxml.jackson.jr.ob.JSON;
import moe.nova.util.JacksonUtil;

import java.io.IOException;

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
    }
}
