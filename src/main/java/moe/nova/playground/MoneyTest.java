package moe.nova.playground;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class MoneyTest {

    public static void main(String[] args) {
        CurrencyUnit currencyUnit = CurrencyUnit.of("CNY");
        Money money = Money.of(currencyUnit, 1000000000000.00);
        System.out.println(money);
        var b = money.getAmount();
        System.out.println(b.toPlainString());
    }
}
