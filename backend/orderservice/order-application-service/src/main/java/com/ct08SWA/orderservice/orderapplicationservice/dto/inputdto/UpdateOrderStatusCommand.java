package com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto;

import com.ct08SWA.orderservice.orderdomaincore.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateOrderStatusCommand(
        @NotNull UUID orderId,
        @NotNull OrderStatus newStatus
) {}