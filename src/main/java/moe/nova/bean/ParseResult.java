package moe.nova.bean;

import org.apache.commons.lang3.StringUtils;

public sealed interface ParseResult {

    static ParseResult parseInt(String toParse) {
        if (toParse == null)
            return new Error("Null string");

        int result = 0;
        for (int i = 0; i < toParse.length(); i++) {
            char current = toParse.charAt(i);
            if (!Character.isDigit(current)) {
                return new Error("Non-digit character found");
            }
            // use bit shifting to convert char to int
            result = (result << 3) + (result << 1) + (current - '0');
        }
        return new Success(result);
    }

    default int getOrDefault(int defaultValue) {
        if (this instanceof Success(int num)) {
            return num;
        }
        return defaultValue;
    }
}

record Success(int num) implements ParseResult {
}

record Error(String errorMsg) implements ParseResult {

    Error {
        if (StringUtils.isEmpty(errorMsg)) {
            throw new IllegalArgumentException("Param [errorMsg] cannot be empty!");
        }
    }
}

class ResultTest {
    static void main() {
        ParseResult r = ParseResult.parseInt("Hello World");
        IO.println(r);
        IO.println(r.getOrDefault(-1));

        ParseResult r1 = ParseResult.parseInt("1234567890");
        IO.println(r1);
        IO.println(r1.getOrDefault(-1));
    }
}
