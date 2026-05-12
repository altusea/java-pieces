package moe.nova.playground;

import moe.nova.util.ConsoleUtil;
import org.apache.commons.lang3.StringUtils;

public class StringTest {

    static void main() {
        String s = """
                第一行
                第二行
                第三行""";
        IO.println(s);

        ConsoleUtil.printSeparateLine();

        String s2 = """
                第一行 \
                第二行 \
                第三行""";
        IO.println(s2);

        System.out.println(StringUtils.isNoneBlank(null));
        System.out.println(StringUtils.isNoneBlank(null, null));
        System.out.println(StringUtils.isNoneBlank((String) null));

        String a= getString();
        System.out.println(StringUtils.isNoneBlank(a));

        var person = new Person(null, 0);
        System.out.println(StringUtils.isNoneBlank(person.name()));

        var person2 = new Person2();
        System.out.println(StringUtils.isNoneBlank(person2.getName()));
    }

    static String getString() {
        return null;
    }

    record Person(String name, int age) {
    }

    static class Person2 {
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
