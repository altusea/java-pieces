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

    public static void main(String[] args) {
        System.out.println(ConvertUtil.digitToChinese(12356));
        System.out.println(ConvertUtil.digitToChinese(10045.14));

        printSeparateLine();
        System.out.println(MaskingUtil.mobilePhone("13589642210"));
        System.out.println(MaskingUtil.idCardNum("140214200009093140", 4, 4));
        System.out.println(MaskingUtil.chineseName("李三"));
        System.out.println(MaskingUtil.chineseName("张北海"));
        System.out.println(IdcardUtil.isValidCard18("140214200009093140"));
        System.out.println(IdcardUtil.isValidHkMoHomeReturn("H12345678"));
        System.out.println(IdcardUtil.isValidHkMoHomeReturn("M1234567801"));
        System.out.println(MaskingUtil.idCardNum("H12345678", 4, 4));
        System.out.println(MaskingUtil.idCardNum("12345678", 4, 4));

        printSeparateLine();
        Date date = new Date();
        System.out.println(DateUtil.beginOfDay(date));
        System.out.println(DateUtil.endOfDay(date, false).millisecond());
        System.out.println(DateUtil.endOfDay(date, true).millisecond());
        System.out.println(DateUtil.offsetYear(date, 64));

        printSeparateLine();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(TimeUtil.toEpochMilli(TimeUtil.endOfDay(localDateTime, false)));
        System.out.println(TimeUtil.toEpochMilli(TimeUtil.endOfDay(localDateTime, true)));
    }
}
