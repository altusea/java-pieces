package moe.nova.playground.jep;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventStore {

    public List<ShoppingCart.Event> mockEvents(UUID shoppingCartId, UUID clientId) {
        // mock
        var productItem1 = new ProductItems.PricedProductItem(UUID.randomUUID(), 100, 10.00);
        var productItem2 = new ProductItems.PricedProductItem(UUID.randomUUID(), 200, 20.00);

        var eventList = new ArrayList<ShoppingCart.Event>();
        eventList.add(new ShoppingCart.Event.Opened(shoppingCartId, clientId, OffsetDateTime.now()));
        eventList.add(new ShoppingCart.Event.ProductItemAdded(shoppingCartId, productItem1, OffsetDateTime.now()));
        eventList.add(new ShoppingCart.Event.ProductItemAdded(shoppingCartId, productItem2, OffsetDateTime.now()));
        eventList.add(new ShoppingCart.Event.ProductItemRemoved(shoppingCartId, productItem1, OffsetDateTime.now()));
        eventList.add(new ShoppingCart.Event.Confirmed(shoppingCartId, OffsetDateTime.now()));
        return eventList;
    }
}
