package moe.nova.util.security;

import moe.nova.bean.DemoClazz;
import moe.nova.util.JacksonUtil;
import org.dromara.hutool.core.codec.binary.HexUtil;
import org.dromara.hutool.crypto.asymmetric.KeyType;
import org.dromara.hutool.crypto.asymmetric.SM2;
import org.dromara.hutool.crypto.bc.SmUtil;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.function.Function;

public class OneNaiveScheme {

    private OneNaiveScheme() {
    }

    public static <T extends Timestamped> MessageStruct construct(T content, Function<T, String> contentMapper,
                                                                  PrivateKey senderPrvKey,
                                                                  PublicKey receiverPubKey) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        content.setCallTimestamp(now);
        String strToSign = "Caller:" + content.getCallerMark() + ":" + content.getCallTimestamp() + ":" + contentMapper.apply(content);
        String cipher = Base64.getEncoder().encodeToString(new SM2(null, receiverPubKey).encrypt(JacksonUtil.toJson(content).getBytes()));
        String sign = HexUtil.encodeStr(new SM2(senderPrvKey, null).sign(strToSign.getBytes()));
        return new MessageStruct(cipher, sign);
    }

    public static <T extends Timestamped> boolean check(MessageStruct messageStruct,
                                                        Class<T> contentClazz,
                                                        Function<T, String> contentMapper,
                                                        PublicKey senderPubKey, PrivateKey receiverPrvKey,
                                                        Duration acceptableDelay) {
        LocalDateTime now = LocalDateTime.now();
        String plain = new String(new SM2(receiverPrvKey, null).decrypt(Base64.getDecoder().decode(messageStruct.cipher()), KeyType.PrivateKey));
        T content = JacksonUtil.fromJson(plain, contentClazz);
        LocalDateTime callTimestamp = LocalDateTime.parse(content.getCallTimestamp(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (Duration.between(callTimestamp, now).abs().compareTo(acceptableDelay) > 0) return false;
        String strToSign = "Caller:" + content.getCallerMark() + ":" + content.getCallTimestamp() + ":" + contentMapper.apply(content);
        return new SM2(null, senderPubKey).verify(strToSign.getBytes(), HexUtil.decode(messageStruct.sign()));
    }

    static void main(String[] args) {
        var sender = SmUtil.sm2();
        var receiver = SmUtil.sm2();

        var demoObj = new DemoClazz();
        demoObj.setFieldA("aaa");
        demoObj.setFieldB("bbb");

        var struct = construct(demoObj, DemoClazz::getFieldA, sender.getPrivateKey(), receiver.getPublicKey());
        IO.println(check(struct, DemoClazz.class, DemoClazz::getFieldA, sender.getPublicKey(), receiver.getPrivateKey(), Duration.ofSeconds(30L)));
    }
}
