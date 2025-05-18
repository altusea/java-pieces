package moe.nova.util;

import com.google.common.collect.Lists;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.IntStream;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class RandomStringGenerator {

    private static final char[] ALPHABETS = new char[]{
            'a', 'c', 'e', 'n', 'o', 'r', 's', 'u'
    };

    private static final char[] ALPHABETS2 = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'
    };

    public static String generateRandomString(int length, char[] alphabets) {
        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        char previousChar = '\0'; // Placeholder for the previous character

        for (int i = 0; i < length; i++) {
            char randomChar;

            do {
                randomChar = alphabets[random.nextInt(alphabets.length)];
            } while (randomChar == previousChar); // Ensure the current character is different from the previous character

            sb.append(randomChar);
            previousChar = randomChar;
        }

        return sb.toString();
    }

    public static void prettyPrint(List<String> stringList) {
        List<List<String>> partitions = Lists.partition(stringList, 20);
        partitions.forEach(e -> IO.println(String.join(" ", e)));
    }

    public static void main(String[] args) {
        List<String> res = IntStream.rangeClosed(1, 100)
                .mapToObj(_ -> generateRandomString(9, ALPHABETS))
                .toList();
        prettyPrint(res);

        printSeparateLine();

        List<String> res2 = IntStream.rangeClosed(1, 100)
                .mapToObj(_ -> generateRandomString(9, ALPHABETS2))
                .toList();
        prettyPrint(res2);
    }
}
