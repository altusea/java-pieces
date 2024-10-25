package moe.nova.playground.io;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class FileTest {

    private static final Logger log = LoggerFactory.getLogger(FileTest.class);

    public static void main(String[] args) {
        try {
            File tempFile = Files.createTempFile("content", null).toFile();
            System.out.println(tempFile.getAbsolutePath());
            URL url = new URI("https://psstatic.cdn.bcebos.com/video/wiseindex/aa6eef91f8b5b1a33b454c401_1660835115000.png").toURL();
            FileUtils.copyURLToFile(url, tempFile);
            System.out.println(FileUtils.sizeOf(tempFile));
            FileUtils.delete(tempFile);

        } catch (IOException | URISyntaxException _) {
            log.info("exception threw ...");
        }
    }
}
