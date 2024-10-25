package moe.nova.playground;

import org.dromara.hutool.crypto.bc.SmUtil;

public class DigestTest {

    public static void main(String[] args) {
        var hmac = SmUtil.hmacSm3("1234qwerasdfzxcv".getBytes());
        var content = "我爱你，玻璃；我能吞下？".getBytes();
        System.out.println(hmac.digestHex(content));
    }
}
