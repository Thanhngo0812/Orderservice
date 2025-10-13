package com.ct08SWA.orderservice.orderdomaincore.event;
import java.time.ZonedDateTime;

import com.ct08SWA.orderservice.orderdomaincore.entity.Order;

public class OrderCreatedEvent implements OrderEvent {
    private final Order order;
    private final ZonedDateTime createdAt;

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}