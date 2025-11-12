package com.ct08SWA.orderservice.orderapplicationservice.handler;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderStatusCommand;
import com.ct08SWA.orderservice.orderapplicationservice.ports.outputports.OrderRepository;
import com.ct08SWA.orderservice.orderdomaincore.entity.Order;
import com.ct08SWA.orderservice.orderdomaincore.valueobject.OrderId;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusUpdateHelper {
    private static final Logger log = LoggerFactory.getLogger(OrderStatusUpdateHelper.class);

    private final OrderRepository orderRepository;

    public OrderStatusUpdateHelper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order persistUpdateOrderStatus(UpdateOrderStatusCommand command) {
        OrderId orderId = new OrderId(command.orderId());
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + command.orderId()));

        order.updateStatus(command.newStatus());

        Order saved = orderRepository.save(order);
        log.info("Order status updated: id={}, newStatus={}", saved.getId().getValue(), saved.getOrderStatus());
        return saved;
    }
}
