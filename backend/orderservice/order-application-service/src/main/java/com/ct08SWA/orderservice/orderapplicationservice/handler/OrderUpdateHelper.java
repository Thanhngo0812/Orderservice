package com.ct08SWA.orderservice.orderapplicationservice.handler;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.mapper.OrderDataMapper;
import com.ct08SWA.orderservice.orderapplicationservice.ports.outputports.OrderRepository;
import com.ct08SWA.orderservice.orderdomaincore.entity.Order;
import com.ct08SWA.orderservice.orderdomaincore.valueobject.OrderId;

import jakarta.transaction.Transactional;

@Component
public class OrderUpdateHelper {
    private static final Logger log = LoggerFactory.getLogger(OrderUpdateHelper.class);

    private final OrderRepository orderRepository;
    private final OrderDataMapper orderDataMapper;

    public OrderUpdateHelper(OrderRepository orderRepository, OrderDataMapper orderDataMapper) {
        this.orderRepository = orderRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public Order persistUpdateOrder(UpdateOrderCommand command) {
        OrderId orderId = new OrderId(command.orderId());
        Order existing = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + command.orderId()));

        var newItems = orderDataMapper.updateCommandItemsToOrderItems(command.items());
        Order updated = existing.updateOrderItems(newItems);

        Order saved = orderRepository.save(updated);
        log.info("Order updated with id: {}", saved.getId().getValue());
        return saved;
    }
}

