package moe.nova.playground.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SymmetricTest {

    public static void main(String[] args) {
        try {
            String plaintext = "Hello, World!"; // 要加密的明文

            // 生成密钥
            String key = "0123456789abcdef"; // 密钥必须是16、24或32个字符长
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            // 创建加密器
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // 加密
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            // 将加密结果转换为Base64字符串
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("加密结果：" + encryptedText);

            // 创建解密器
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // 解密
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

            // 将解密结果转换为字符串
            String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
            System.out.println("解密结果：" + decryptedText);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
