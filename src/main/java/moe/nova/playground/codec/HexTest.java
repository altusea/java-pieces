package moe.nova.playground.codec;

import org.apache.commons.codec.binary.Hex;

public class HexTest {

    static void main(String[] args) {
        var content = "Hello World";
        var encoded = Hex.encodeHexString(content.getBytes());
        IO.println(encoded);
    }
}
