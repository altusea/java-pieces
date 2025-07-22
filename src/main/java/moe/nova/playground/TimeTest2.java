package moe.nova.playground;

import cn.hutool.v7.core.date.DateUtil;

import java.time.Instant;
import java.util.Date;

public class TimeTest2 {

    static void main(String[] args) {
        IO.println(System.currentTimeMillis());
        IO.println(Instant.now().toEpochMilli());

        String a = String.valueOf(System.currentTimeMillis());
        IO.println(a);
        Date date = DateUtil.date(System.currentTimeMillis());
        IO.println(date);
        IO.println(DateUtil.date(1725870017639L));
        IO.println(DateUtil.date(Integer.MAX_VALUE));

        var date1 = DateUtil.parse("Fri Feb 29 18:03:09 CST 1732");
        IO.println(DateUtil.toInstant(date1).toEpochMilli());
        IO.println(Long.MAX_VALUE);
    }
}
