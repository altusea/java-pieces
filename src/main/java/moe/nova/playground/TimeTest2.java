package moe.nova.playground;

import org.dromara.hutool.core.date.DateUtil;

import java.time.Instant;
import java.util.Date;

public class TimeTest2 {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(Instant.now().toEpochMilli());

        String a = String.valueOf(System.currentTimeMillis());
        System.out.println(a);
        Date date = DateUtil.date(System.currentTimeMillis());
        System.out.println(date);
        System.out.println(DateUtil.date(1725870017639L));
        System.out.println(DateUtil.date(Integer.MAX_VALUE));

        var date1 = DateUtil.parse("Fri Feb 29 18:03:09 CST 1732");
        System.out.println(DateUtil.toInstant(date1).toEpochMilli());
        System.out.println(Long.MAX_VALUE);
    }
}
