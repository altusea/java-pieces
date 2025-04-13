package moe.nova.playground;

import org.dromara.hutool.core.date.DateFormatPool;
import org.dromara.hutool.core.date.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeTest3 {

    public static void main(String[] args) {
        var date1 = LocalDate.parse("20250312", DateFormatPool.PURE_DATE_FORMATTER);
        var date2 = LocalDate.parse("20250315", DateFormatPool.PURE_DATE_FORMATTER);
        System.out.println(TimeUtil.betweenPeriod(date1, date2).getDays());

        date1 = LocalDate.parse("20191212", DateFormatPool.PURE_DATE_FORMATTER);
        date2 = LocalDate.parse("20221212", DateFormatPool.PURE_DATE_FORMATTER);
        System.out.println(TimeUtil.betweenPeriod(date1, date2).getYears());

        System.out.println(LocalDateTime.now().minusMonths(1L).format(DateFormatPool.NORM_DATETIME_FORMATTER));
    }
}
