package moe.nova.playground.jep;

import java.util.List;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

sealed interface ItemPrice {

    static ItemPrice itemPriceFromWeb() {
        return switch ((int) (Math.random() * 3)) { // random number between 0 and 2
            case 0 -> new Sale(100.0);
            case 1 -> new Trade(List.of("Phone", "Laptop"));
            default -> new ContactMe();
        };
    }
}

record Sale(double price) implements ItemPrice {
}

record Trade(List<String> tradeOptions) implements ItemPrice {
}

record ContactMe() implements ItemPrice {
}

class ItemPriceTest {

    public static void main(String[] args) {
        ItemPrice sale1 = new Sale(100.0);
        System.out.println(sale1);
        ItemPrice trade1 = new Trade(List.of("Phone", "Laptop"));
        System.out.println(trade1);
        ItemPrice contactMe1 = new ContactMe();
        System.out.println(contactMe1);

        printSeparateLine();
        switch (ItemPrice.itemPriceFromWeb()) {
            case Sale sale -> System.out.println("Sale price: " + sale.price());
            case Trade trade -> System.out.println("Trade options: " + trade.tradeOptions());
            case ContactMe _ -> System.out.println("Contact me for price");
        }
    }
}
