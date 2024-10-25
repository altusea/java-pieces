package moe.nova.playground.jep;

import java.time.OffsetDateTime;
import java.util.UUID;

import static moe.nova.playground.jep.FunctionalTools.when;

public sealed interface ShoppingCart {

    record Initial() implements ShoppingCart {
    }

    record Pending(ProductItems ProductItems) implements ShoppingCart {
    }

    record Closed() implements ShoppingCart {
    }


    sealed interface Event {

        record Opened(
                UUID shoppingCartId,
                UUID clientId,
                OffsetDateTime openedAt
        ) implements Event {
        }

        record ProductItemAdded(
                UUID shoppingCartId,
                ProductItems.PricedProductItem productItem,
                OffsetDateTime addedAt
        ) implements Event {
        }

        record ProductItemRemoved(
                UUID shoppingCartId,
                ProductItems.PricedProductItem productItem,
                OffsetDateTime removedAt) implements Event {
        }

        record Confirmed(
                UUID shoppingCartId,
                OffsetDateTime confirmedAt
        ) implements Event {
        }

        record Canceled(
                UUID shoppingCartId,
                OffsetDateTime canceledAt
        ) implements Event {
        }

        static ShoppingCart getShoppingCart(Event[] events) {
            ShoppingCart state = new Initial();

            for (var event : events) {
                state = evolve(state, event);
            }
            return state;
        }

        static ShoppingCart evolve(ShoppingCart state, Event event) {
            return switch (when(state, event)) {
                case FunctionalTools.When(Initial _, Opened _) -> new Pending(ProductItems.empty());

                case FunctionalTools.When(
                        Pending(var productItems),
                        ProductItemAdded(_, var productItem, _)
                ) -> new Pending(productItems.add(productItem));

                case FunctionalTools.When(
                        Pending(var productItems),
                        ProductItemRemoved(_, var productItem, _)
                ) -> new Pending(productItems.remove(productItem));

                case FunctionalTools.When(Pending _, Confirmed _),
                     FunctionalTools.When(Pending _, Canceled _) -> new Closed();

                default -> state;
            };
        }
    }
}
