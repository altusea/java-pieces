package moe.nova.playground;

import org.dromara.hutool.core.convert.ConvertUtil;
import org.dromara.hutool.core.data.IdcardUtil;
import org.dromara.hutool.core.data.MaskingUtil;
import org.dromara.hutool.core.date.DateUtil;
import org.dromara.hutool.core.date.TimeUtil;

import java.time.LocalDateTime;
import java.util.Date;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class HutoolTest {

    static void main(String[] args) {
        IO.println(ConvertUtil.digitToChinese(12356));
        IO.println(ConvertUtil.digitToChinese(10045.14));

        printSeparateLine();
        IO.println(MaskingUtil.mobilePhone("13589642210"));
        IO.println(MaskingUtil.idCardNum("140214200009093140", 4, 4));
        IO.println(MaskingUtil.chineseName("李三"));
        IO.println(MaskingUtil.chineseName("张北海"));
        IO.println(IdcardUtil.isValidCard18("140214200009093140"));
        IO.println(IdcardUtil.isValidHkMoHomeReturn("H12345678"));
        IO.println(IdcardUtil.isValidHkMoHomeReturn("M1234567801"));
        IO.println(MaskingUtil.idCardNum("H12345678", 4, 4));
        IO.println(MaskingUtil.idCardNum("12345678", 4, 4));

        printSeparateLine();
        Date date = new Date();
        IO.println(DateUtil.beginOfDay(date));
        IO.println(DateUtil.endOfDay(date, false).millisecond());
        IO.println(DateUtil.endOfDay(date, true).millisecond());
        IO.println(DateUtil.offsetYear(date, 64));

        printSeparateLine();
        LocalDateTime localDateTime = LocalDateTime.now();
        IO.println(TimeUtil.toEpochMilli(TimeUtil.endOfDay(localDateTime, false)));
        IO.println(TimeUtil.toEpochMilli(TimeUtil.endOfDay(localDateTime, true)));
    }
}
