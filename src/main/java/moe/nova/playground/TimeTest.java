package moe.nova.playground;

import cn.hutool.v7.core.date.TimeUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class TimeTest {

    static void main(String[] args) throws InterruptedException {
        Instant instant1 = Instant.now();
        Thread.sleep(Duration.ofSeconds(1));
        var instant2 = Instant.now();
        IO.println(instant1.isBefore(instant2));

        printSeparateLine();
        String s = DateTimeFormatter.ofPattern("B").format(LocalDateTime.now());
        IO.println(s);

        IO.println("==================== hutool test ====================");
        long thirtyMinutes = 30 * 60 * 1000;
        long timestampNow = System.currentTimeMillis();
        long timestampThirtyMinutesLater = timestampNow + thirtyMinutes;

        IO.println(TimeUtil.of(timestampNow));
        IO.println(TimeUtil.of(timestampThirtyMinutesLater));

        IO.println("\n==================== java built-in time classes ====================");
        LocalTime localTime = LocalTime.now();
        IO.println("LocalTime.now(): " + localTime);
        LocalDate localDate = LocalDate.now();
        IO.println("LocalDate.now(): " + localDate);
        IO.println("LocalDate.now().atStartOfDay(): " + localDate.atStartOfDay());
        LocalDateTime localDateTime = LocalDateTime.now();
        IO.println("LocalDateTime: " + localDateTime);
        IO.println("ZoneId.systemDefault(): " + ZoneId.systemDefault());

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        IO.println("OffsetDateTime.now(): " + offsetDateTime + " with offset " + offsetDateTime.getOffset());
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        IO.println("ZonedDateTime.now(): " + zonedDateTime + " with zone " + zonedDateTime.getZone());

        IO.println("\n==================== other test ====================");
        LocalDateTime fromDate = TimeUtil.of(new Date());
        IO.println(fromDate);

        IO.println("\n==================== YearMonth test ====================");
        YearMonth yearMonth = YearMonth.now();
        IO.println(yearMonth);
        IO.println(yearMonth.atEndOfMonth());
    }
}
