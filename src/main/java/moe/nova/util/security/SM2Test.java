package moe.nova.util.security;

import cn.hutool.v7.crypto.asymmetric.SM2;

public class SM2Test {

    static void main() {
        SM2 sm2 = new SM2(); // 生成密钥对
        var pubKeyBase64 = sm2.getPublicKeyBase64(); // 公钥
        var priKeyBase64 = sm2.getPrivateKeyBase64(); // 私钥
        IO.println(pubKeyBase64);
        IO.println(priKeyBase64);
    }
}
