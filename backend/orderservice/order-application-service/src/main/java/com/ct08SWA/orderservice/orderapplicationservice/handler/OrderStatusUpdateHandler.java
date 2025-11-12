package com.ct08SWA.orderservice.orderapplicationservice.handler;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderStatusCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.UpdateOrderStatusResponse;
import com.ct08SWA.orderservice.orderapplicationservice.mapper.OrderDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusUpdateHandler {
    private static final Logger log = LoggerFactory.getLogger(OrderStatusUpdateHandler.class);

    private final OrderStatusUpdateHelper orderStatusUpdateHelper;
    private final OrderDataMapper orderDataMapper;

    public OrderStatusUpdateHandler(OrderStatusUpdateHelper orderStatusUpdateHelper,
                                    OrderDataMapper orderDataMapper) {
        this.orderStatusUpdateHelper = orderStatusUpdateHelper;
        this.orderDataMapper = orderDataMapper;
    }

    public UpdateOrderStatusResponse updateOrderStatus(UpdateOrderStatusCommand command) {
        var updatedOrder = orderStatusUpdateHelper.persistUpdateOrderStatus(command);
        log.info("Order status updated, id: {}, newStatus: {}", updatedOrder.getId().getValue(), updatedOrder.getOrderStatus());
        return orderDataMapper.orderToUpdateOrderStatusResponse(updatedOrder);
    }
}
