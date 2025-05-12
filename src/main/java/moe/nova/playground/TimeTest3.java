package moe.nova.playground;

import org.dromara.hutool.core.date.DateFormatPool;
import org.dromara.hutool.core.date.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeTest3 {

    public static void main(String[] args) {
        var date1 = LocalDate.parse("20250312", DateFormatPool.PURE_DATE_FORMATTER);
        var date2 = LocalDate.parse("20250315", DateFormatPool.PURE_DATE_FORMATTER);
        System.out.println(TimeUtil.betweenPeriod(date1, date2).getDays());
        System.out.println(ChronoUnit.DAYS.between(date1, date2));

        date1 = LocalDate.parse("20191212", DateFormatPool.PURE_DATE_FORMATTER);
        date2 = LocalDate.parse("20221212", DateFormatPool.PURE_DATE_FORMATTER);
        System.out.println(TimeUtil.betweenPeriod(date1, date2).getYears());
        System.out.println(ChronoUnit.YEARS.between(date1, date2));

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.minusMonths(1L));

        LocalDateTime someTime = LocalDateTime.of(2025, 3, 31, 0, 5, 30);
        System.out.println(someTime);
        System.out.println(someTime.minusMonths(1L));
    }
}
