package moe.nova.util;

import cn.hutool.v7.crypto.symmetric.SM4;
import org.apache.commons.codec.binary.Hex;

import java.security.Security;

public class CipherUtil {

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    static void main(String[] args) {
        var sm4 = new SM4();
        IO.println(Hex.encodeHexString(sm4.getSecretKey().getEncoded()));
    }
}
