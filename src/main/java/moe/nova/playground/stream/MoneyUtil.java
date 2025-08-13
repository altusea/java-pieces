package moe.nova.playground.stream;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;

public class MoneyUtil {

    public static Gatherer<Money, ?, Money> computeBalanceHistory(Money balance) {
        return Gatherers.scan(() -> balance, Money::add);
    }

    static void main() {
        var CNY = Currency.getInstance("CNY");

        var transactions = List.of(
                new Money(BigDecimal.valueOf(-10), CNY),
                new Money(BigDecimal.valueOf(20), CNY),
                new Money(BigDecimal.valueOf(50), CNY)
        );

        var balance = new Money(BigDecimal.valueOf(270), CNY);

        IO.println("Balance history:");
        transactions.stream()
                .gather(MoneyUtil.computeBalanceHistory(balance))
                .forEach(System.out::println);
    }
}
