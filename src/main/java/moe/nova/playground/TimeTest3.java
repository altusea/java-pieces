package moe.nova.playground;

import cn.hutool.v7.core.date.DateFormatPool;
import cn.hutool.v7.core.date.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeTest3 {

    static void main(String[] args) {
        var date1 = LocalDate.parse("20250312", DateFormatPool.PURE_DATE_FORMATTER);
        var date2 = LocalDate.parse("20250315", DateFormatPool.PURE_DATE_FORMATTER);
        IO.println(TimeUtil.betweenPeriod(date1, date2).getDays());
        IO.println(ChronoUnit.DAYS.between(date1, date2));

        date1 = LocalDate.parse("20191212", DateFormatPool.PURE_DATE_FORMATTER);
        date2 = LocalDate.parse("20221212", DateFormatPool.PURE_DATE_FORMATTER);
        IO.println(TimeUtil.betweenPeriod(date1, date2).getYears());
        IO.println(ChronoUnit.YEARS.between(date1, date2));

        LocalDateTime now = LocalDateTime.now();
        IO.println(now);
        IO.println(now.minusMonths(1L));

        LocalDateTime someTime = LocalDateTime.of(2025, 3, 31, 0, 5, 30);
        IO.println(someTime);
        IO.println(someTime.minusMonths(1L));
    }
}
