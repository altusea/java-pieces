package moe.nova.playground.jep;

import java.time.OffsetDateTime;
import java.util.UUID;

import static moe.nova.playground.jep.FunctionalTools.on;

public class ShoppingCartDecider {

    public sealed interface Command {
        record Open(
                UUID shoppingCartId,
                UUID clientId,
                OffsetDateTime now
        ) implements Command {
        }

        record AddProductItem(
                UUID shoppingCartId,
                ProductItems.PricedProductItem productItem,
                OffsetDateTime now
        ) implements Command {
        }

        record RemoveProductItem(
                UUID shoppingCartId,
                ProductItems.PricedProductItem productItem,
                OffsetDateTime now
        ) implements Command {
        }

        record Confirm(
                UUID shoppingCartId,
                OffsetDateTime now
        ) implements Command {
        }

        record Cancel(
                UUID shoppingCartId,
                OffsetDateTime now
        ) implements Command {
        }
    }

    public static ShoppingCart.Event decide(Command command, ShoppingCart state) {
        return switch (on(state, command)) {
            case FunctionalTools.On(
                    ShoppingCart.Initial _,
                    Command.Open(var id, var clientId, var now)
            ) -> new ShoppingCart.Event.Opened(id, clientId, now);

            case FunctionalTools.On(
                    ShoppingCart.Pending _,
                    Command.AddProductItem(var id, var productItem, var now)
            ) -> new ShoppingCart.Event.ProductItemAdded(id, productItem, now);

            case FunctionalTools.On(
                    ShoppingCart.Pending(var productItems),
                    Command.RemoveProductItem(var id, var productItem, var now)
            ) -> {
                if (!productItems.hasEnough(productItem))
                    throw new IllegalStateException("Not enough product items to remove");

                yield new ShoppingCart.Event.ProductItemRemoved(id, productItem, now);
            }

            case FunctionalTools.On(
                    ShoppingCart.Pending _,
                    Command.Confirm(var id, var now)
            ) -> new ShoppingCart.Event.Confirmed(id, now);

            case FunctionalTools.On(
                    ShoppingCart.Pending _,
                    Command.Cancel(var id, var now)
            ) -> new ShoppingCart.Event.Canceled(id, now);

            default -> throw new IllegalStateException(
                    String.format("Cannot %s on %s", command.getClass().getName(), state.getClass().getName())
            );
        };
    }
}
