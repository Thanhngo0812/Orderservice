package com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto;

import java.util.UUID;

import com.ct08SWA.orderservice.orderdomaincore.valueobject.OrderStatus;

public record UpdateOrderResponse(
    UUID orderId,
    OrderStatus orderStatus,
    String message
) {}

