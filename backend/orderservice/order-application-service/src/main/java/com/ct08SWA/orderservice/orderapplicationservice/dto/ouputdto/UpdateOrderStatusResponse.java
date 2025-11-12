package com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto;

import com.ct08SWA.orderservice.orderdomaincore.valueobject.OrderStatus;

import java.util.UUID;

public record UpdateOrderStatusResponse (
        UUID orderId,
        OrderStatus newStatus,
        String message
) {}
