package com.ct08SWA.orderservice.orderapplicationservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.CreateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.OrderCreatedResponse;
import com.ct08SWA.orderservice.orderdomaincore.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import com.ct08SWA.orderservice.orderapplicationservice.mapper.OrderDataMapper;

@Slf4j
@Component
public class OrderCreateCommandHandler {
	private static final Logger log = LoggerFactory.getLogger(OrderCreateCommandHandler.class);

    private final OrderCreateHelper orderCreateHelper;
    private final OrderDataMapper orderDataMapper;

    public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper, 
                                   OrderDataMapper orderDataMapper) {
        this.orderCreateHelper = orderCreateHelper;
        this.orderDataMapper = orderDataMapper;
    }

    public OrderCreatedResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }
}