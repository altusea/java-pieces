package moe.nova.playground;

import cn.hutool.v7.core.exception.ExceptionUtil;
import com.google.common.util.concurrent.Runnables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.SocketTimeoutException;

import static moe.nova.util.FunctionalUtil.invokeSafely;

public class ExceptionTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionTest.class);

    public static void throwCheckException() throws IOException {
        throw new SocketTimeoutException();
    }

    static void main() {
        new Thread(Runnables.doNothing()).start();

        var e1 = new RuntimeException();
        var e2 = new UncheckedIOException(new IOException());
        IO.println(ExceptionUtil.getRootCause(e1).getClass());
        IO.println(ExceptionUtil.getRootCause(e1).getClass().getName());
        IO.println(ExceptionUtil.getRootCause(e2).getClass());
        IO.println(ExceptionUtil.getRootCause(e2).getClass().getName());

        var e3 = new RuntimeException("something bad happened ...");
        LOGGER.info("test log ...", e3);
        invokeSafely(ExceptionTest::throwCheckException);
    }
}
