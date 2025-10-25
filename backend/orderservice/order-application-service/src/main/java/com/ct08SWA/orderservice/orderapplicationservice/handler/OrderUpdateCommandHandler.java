package com.ct08SWA.orderservice.orderapplicationservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.UpdateOrderResponse;
import com.ct08SWA.orderservice.orderapplicationservice.mapper.OrderDataMapper;

@Component
public class OrderUpdateCommandHandler {
    private static final Logger log = LoggerFactory.getLogger(OrderUpdateCommandHandler.class);

    private final OrderUpdateHelper orderUpdateHelper;
    private final OrderDataMapper orderDataMapper;

    public OrderUpdateCommandHandler(OrderUpdateHelper orderUpdateHelper, OrderDataMapper orderDataMapper) {
        this.orderUpdateHelper = orderUpdateHelper;
        this.orderDataMapper = orderDataMapper;
    }

    public UpdateOrderResponse updateOrder(UpdateOrderCommand command) {
        var updated = orderUpdateHelper.persistUpdateOrder(command);
        log.info("Order updated, id: {}", updated.getId().getValue());
        return orderDataMapper.orderToUpdateOrderResponse(updated);
    }
}

