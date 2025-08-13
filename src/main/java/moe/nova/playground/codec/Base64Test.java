package moe.nova.playground.codec;

import cn.hutool.v7.core.codec.binary.Base64;
import com.google.common.io.BaseEncoding;

import java.nio.charset.StandardCharsets;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class Base64Test {

    static void main() {
        String url = "https://www.baidu.com?query=时间";
        String a1 = Base64.encode(url);
        IO.println(a1);
        IO.println(Base64.decodeStr(a1)); // aHR0cHM6Ly93d3cuYmFpZHUuY29tP3F1ZXJ0PeaXtumXtA==
        String a2 = Base64.encodeUrlSafe(url);
        IO.println(a2); // aHR0cHM6Ly93d3cuYmFpZHUuY29tP3F1ZXJ0PeaXtumXtA
        IO.println(Base64.decodeStr(a2));

        printSeparateLine();
        String c = "测试一下子啊a";
        byte[] cs = c.getBytes(StandardCharsets.UTF_8);
        IO.println("length of byte array: " + cs.length);

        printSeparateLine();
        IO.println(BaseEncoding.base64Url().encode(cs));
        IO.println(BaseEncoding.base64Url().omitPadding().encode(cs));
        IO.println(java.util.Base64.getUrlEncoder().encodeToString(cs));
        IO.println(java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(cs));

        printSeparateLine();
        var withPadding = "5rWL6K-V5LiA5LiL5a2Q5ZWKYQ==";
        var withoutPadding = "5rWL6K-V5LiA5LiL5a2Q5ZWKYQ";
        printSeparateLine("java.util");
        IO.println(new String(
                java.util.Base64.getUrlDecoder().decode(withPadding.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8));
        IO.println(new String(
                java.util.Base64.getUrlDecoder().decode(withoutPadding.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8));
        printSeparateLine("org.apache.commons.codec");
        IO.println(new String(
                org.apache.commons.codec.binary.Base64.decodeBase64(withPadding.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8));
        IO.println(new String(
                org.apache.commons.codec.binary.Base64.decodeBase64(withoutPadding),
                StandardCharsets.UTF_8));
    }
}
