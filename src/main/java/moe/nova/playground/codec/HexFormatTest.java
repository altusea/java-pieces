package moe.nova.playground.codec;

import java.util.Arrays;
import java.util.HexFormat;

public class HexFormatTest {

    static void main() {
        HexFormat formatUppercase = HexFormat.of().withUpperCase();
        HexFormat formatLowercase = HexFormat.of();

        byte[] input = new byte[]{127, 0, -50, 105};
        IO.println(formatUppercase.formatHex(input));
        IO.println(formatLowercase.formatHex(input));

        byte[] output = formatUppercase.parseHex("7F00CE69");
        IO.println(Arrays.compare(input, output) == 0);
    }
}
