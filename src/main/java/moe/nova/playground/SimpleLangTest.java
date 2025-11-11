package moe.nova.playground;

import org.apache.commons.lang3.StringUtils;

public class SimpleLangTest {

    static int sayHello() {
        IO.println("hello");
        return 1;
    }

    static void main() {
        int a = StringUtils.contains("hello", 'a') ? sayHello() : -1;
        IO.println(a);
    }
}
