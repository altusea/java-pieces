package moe.nova.util;


import org.dromara.hutool.crypto.symmetric.SM4;

public class CipherUtil {
    public static void main(String[] args) {
        var sm4 = new SM4();
        System.out.println(sm4.getSecretKey().getEncoded());
    }
}
