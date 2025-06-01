package moe.nova.playground;

import org.dromara.hutool.core.math.NumberUtil;

import java.math.BigDecimal;

public class NumberTest {

    static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(1.0);
        IO.println(a.toString());
        IO.println(a.toPlainString());
        IO.println(NumberUtil.format("0.00", a));
    }
}
