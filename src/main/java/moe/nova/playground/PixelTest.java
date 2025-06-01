package moe.nova.playground;

import kala.collection.immutable.ImmutableSeq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class PixelTest {

    static void main(String[] args) throws IOException {
        String homeDir = System.getProperty("user.home");
        Path homeDirPath = Paths.get(homeDir);
        Path picturesDirPath = homeDirPath.resolve("Downloads\\pic-test");
        ImmutableSeq.from(Files.list(picturesDirPath)).forEachChecked(p -> {
            IO.println(p);
            var bytes = Files.readAllBytes(p);
            int size = bytes.length;
            if (size % 3 == 0) {
                var base64 = Base64.getEncoder().encodeToString(bytes);
                var b64StrLen = base64.length();
                IO.println(b64StrLen + " " + b64StrLen % 3);
            }
            printSeparateLine();
        });

        IO.println(picturesDirPath);
    }
}
