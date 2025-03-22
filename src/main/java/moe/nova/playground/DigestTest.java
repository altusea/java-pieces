package moe.nova.playground;

import org.apache.commons.codec.digest.DigestUtils;
import org.dromara.hutool.crypto.digest.DigestUtil;

public class DigestTest {

    public static void main(String[] args) {
        var content = "我爱你，玻璃；我能吞下？";
        var contentBytes = content.getBytes();
        System.out.println("test Hex:");
        System.out.println(new String(org.bouncycastle.util.encoders.Hex.encode(contentBytes)));
        System.out.println(org.apache.commons.codec.binary.Hex.encodeHexString(contentBytes));

        System.out.println("\ntest MD5:");
        System.out.println(DigestUtil.md5Hex(content));
        System.out.println(DigestUtils.md5Hex(content));
    }
}
