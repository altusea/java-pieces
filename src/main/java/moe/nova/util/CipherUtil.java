package moe.nova.util;

import org.apache.commons.codec.binary.Hex;
import org.dromara.hutool.crypto.symmetric.SM4;

import java.security.Security;

public class CipherUtil {

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public static void main(String[] args) {
        var sm4 = new SM4();
        IO.println(Hex.encodeHexString(sm4.getSecretKey().getEncoded()));
    }
}
