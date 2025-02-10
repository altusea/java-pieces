package moe.nova.playground;

import org.dromara.hutool.core.exception.ExceptionUtil;
import org.dromara.hutool.core.io.IORuntimeException;

import java.io.IOException;
import java.time.LocalDateTime;

public class LogTest {

    static void test() {
        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogTest.class);
        var a = LocalDateTime.now();
        var b = "Alice";
        log.info("Hello {} @ {}", b, a);

        try {
            test();
        } catch (Exception e) {
            log.error("Error: {}", e.getClass().getName());
        }

        var e1 = new RuntimeException();
        var e2 = new IORuntimeException(new IOException());
        System.out.println(ExceptionUtil.getRootCause(e1).getClass());
        System.out.println(ExceptionUtil.getRootCause(e1).getClass().getName());
        System.out.println(ExceptionUtil.getRootCause(e2).getClass());
        System.out.println(ExceptionUtil.getRootCause(e2).getClass().getName());
    }
}
