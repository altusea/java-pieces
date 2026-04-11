package moe.nova.playground;

import moe.nova.util.ConsoleUtil;

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
    }
}
