package moe.nova.playground;

import org.dromara.hutool.core.math.NumberUtil;

import java.math.BigDecimal;

public class NumberTest {

    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(1.0);
        System.out.println(a.toString());
        System.out.println(a.toPlainString());
        System.out.println(NumberUtil.format("0.00", a));
    }
}
