package moe.nova.playground;

import org.bouncycastle.util.encoders.Base32;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

public class BCTest {

    public static void main(String[] args) {
        var a = "夸克会第一时间上传，其他网盘几小时后上传";
        var bytes = a.getBytes(StandardCharsets.UTF_8);
        IO.println(Hex.toHexString(bytes));
        IO.println(Base32.toBase32String(bytes));
        IO.println(Base64.toBase64String(bytes));
    }
}
