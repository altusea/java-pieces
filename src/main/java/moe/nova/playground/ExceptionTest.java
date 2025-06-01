package moe.nova.playground;

import com.google.common.util.concurrent.Runnables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

import static moe.nova.util.FunctionalUtil.invokeSafely;

public class ExceptionTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionTest.class);

    public static void throwCheckException() throws IOException {
        throw new SocketTimeoutException();
    }

    static void main(String[] args) {
        new Thread(Runnables.doNothing()).start();

        LOGGER.info("test log ...");
        invokeSafely(ExceptionTest::throwCheckException);
    }
}
