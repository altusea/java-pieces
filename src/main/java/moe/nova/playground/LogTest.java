package moe.nova.playground;

import java.time.LocalDateTime;

public class LogTest {

    public static void main(String[] args) {
        final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogTest.class);
        var a = LocalDateTime.now();
        var b = "Alice";
        log.info("Hello {} @ {}", b, a);
    }
}
