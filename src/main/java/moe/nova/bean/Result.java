package moe.nova.bean;

import org.apache.commons.lang3.StringUtils;

public sealed interface Result {

    static Result parseInt(String toParse) {
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

record Success(int num) implements Result {
}

record Error(String errorMsg) implements Result {

    Error {
        if (StringUtils.isEmpty(errorMsg)) {
            throw new IllegalArgumentException("Param [errorMsg] cannot be empty!");
        }
    }
}

class ResultTest {
    public static void main(String[] args) {
        Result r = Result.parseInt("Hello World");
        System.out.println(r);
        System.out.println(r.getOrDefault(-1));

        Result r1 = Result.parseInt("1234567890");
        System.out.println(r1);
        System.out.println(r1.getOrDefault(-1));
    }
}
