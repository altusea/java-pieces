package moe.nova.playground;

import cn.hutool.v7.crypto.digest.DigestUtil;
import org.apache.commons.codec.digest.DigestUtils;

public class DigestTest {

    static void main() {
        var content = "我爱你，玻璃；我能吞下？";
        var contentBytes = content.getBytes();
        IO.println("test Hex:");
        IO.println(new String(org.bouncycastle.util.encoders.Hex.encode(contentBytes)));
        IO.println(org.apache.commons.codec.binary.Hex.encodeHexString(contentBytes));

        IO.println("\ntest MD5:");
        IO.println(DigestUtil.md5Hex(content));
        IO.println(DigestUtils.md5Hex(content));
    }
}
