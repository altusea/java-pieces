package moe.nova.playground;

import moe.nova.util.ConsoleUtil;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TimeTest4 {

    static void main() {
        LocalDate d1 = LocalDate.of(2022, 8,1);
        LocalDate d2 = LocalDate.now();
        var period = Period.between(d1, d2);
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(period.getYears());

        ConsoleUtil.printSeparateLine();
        var period2 = Period.between(d2, d1);
        System.out.println(period2.getDays());
        System.out.println(period2.getMonths());
        System.out.println(period2.getYears());

        ConsoleUtil.printSeparateLine();
        var period3 = ChronoUnit.MONTHS.between(d1, d2);
        System.out.println(period3);
    }
}
