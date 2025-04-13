package moe.nova.playground;

import com.github.f4b6a3.tsid.TsidCreator;
import org.dromara.hutool.core.data.id.NanoId;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class UniqueIdTest {

    public static void main(String[] args) {
        System.out.println("generate a nanoid which is url-friendly:");
        System.out.println(NanoId.randomNanoId());

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
