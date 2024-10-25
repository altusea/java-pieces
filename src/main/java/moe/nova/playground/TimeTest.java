package moe.nova.playground;

import org.dromara.hutool.core.date.TimeUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class TimeTest {

    public static void main(String[] args) throws InterruptedException {
        Instant instant1 = Instant.now();
        Thread.sleep(Duration.ofSeconds(1));
        var instant2 = Instant.now();
        System.out.println(instant1.isBefore(instant2));

        printSeparateLine();
        String s = DateTimeFormatter.ofPattern("B").format(LocalDateTime.now());
        System.out.println(s);

        System.out.println("==================== hutool test ====================");
        long thirtyMinutes = 30 * 60 * 1000;
        long timestampNow = System.currentTimeMillis();
        long timestampThirtyMinutesLater = timestampNow + thirtyMinutes;

        System.out.println(TimeUtil.of(timestampNow));
        System.out.println(TimeUtil.of(timestampThirtyMinutesLater));

        System.out.println("\n==================== java built-in time classes ====================");
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime.now(): " + localTime);
        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate.now(): " + localDate);
        System.out.println("LocalDate.now().atStartOfDay(): " + localDate.atStartOfDay());
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime: " + localDateTime);
        System.out.println("ZoneId.systemDefault(): " + ZoneId.systemDefault());

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("OffsetDateTime.now(): " + offsetDateTime + " with offset " + offsetDateTime.getOffset());
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime.now(): " + zonedDateTime + " with zone " + zonedDateTime.getZone());

        System.out.println("\n==================== other test ====================");
        LocalDateTime fromDate = TimeUtil.of(new Date());
        System.out.println(fromDate);

        System.out.println("\n==================== YearMonth test ====================");
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.atEndOfMonth());
    }
}
