package com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record UpdateOrderCommand(
    @NotNull UUID orderId,
    @NotNull List<OrderItemData> items
) {
    public record OrderItemData(
        @NotNull UUID productId,
        @NotNull Integer quantity,
        @NotNull BigDecimal price,
        @NotNull BigDecimal subTotal
    ) {}
}

