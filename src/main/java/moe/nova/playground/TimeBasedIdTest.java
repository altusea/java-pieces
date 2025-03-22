package moe.nova.playground;

import com.github.f4b6a3.tsid.TsidCreator;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class TimeBasedIdTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(TsidCreator.getTsid256().toLong());
        }

        printSeparateLine();
        var a = TsidCreator.getTsid256();
        System.out.println(a); // '0K692CY3072M9', 13 chars
        System.out.println(a.toLong()); // '691621845803436681', 18 digits
        System.out.println(a.getInstant());
    }
}
