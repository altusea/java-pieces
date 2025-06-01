package moe.nova.playground.io;

import com.google.common.io.ByteSource;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class IOTest {

    static void main(String[] args) throws IOException {
        String a = "hello, world";
        byte[] bytes = a.getBytes(StandardCharsets.UTF_8);

        try (InputStream inputStream = ByteSource.wrap(bytes).openStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            IOUtils.copy(inputStream, outputStream);
            IO.println(outputStream.toString(StandardCharsets.UTF_8));
        }
    }
}
