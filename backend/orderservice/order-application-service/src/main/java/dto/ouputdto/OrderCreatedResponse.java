package dto.ouputdto;
import java.util.UUID;

import valueobject.OrderStatus;

public record OrderCreatedResponse(
    UUID orderTrackingId,
    OrderStatus orderStatus,
    String message
) {
    public OrderCreatedResponse(UUID orderTrackingId, OrderStatus orderStatus) {
        this(orderTrackingId, orderStatus, "Order created successfully");
    }
}

