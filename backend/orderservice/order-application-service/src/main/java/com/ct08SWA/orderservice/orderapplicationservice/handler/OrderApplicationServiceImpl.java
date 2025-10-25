package com.ct08SWA.orderservice.orderapplicationservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.CreateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.OrderCreatedResponse;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.UpdateOrderResponse;
import lombok.extern.slf4j.Slf4j;
import com.ct08SWA.orderservice.orderapplicationservice.ports.inputports.OrderApplicationService;
//import rest.OrderController;

@Slf4j
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {
	private static final Logger log = LoggerFactory.getLogger(OrderApplicationServiceImpl.class);

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderUpdateCommandHandler orderUpdateCommandHandler;

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                       OrderUpdateCommandHandler orderUpdateCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderUpdateCommandHandler = orderUpdateCommandHandler;
    }

    @Override
    public OrderCreatedResponse createOrder(CreateOrderCommand createOrderCommand) {
        log.info("Received create order command for customer: {} and restaurant: {}", 
                createOrderCommand.customerId(), createOrderCommand.restaurantId());
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public UpdateOrderResponse updateOrder(UpdateOrderCommand updateOrderCommand) {
        log.info("Received update order command for order: {}", updateOrderCommand.orderId());
        return orderUpdateCommandHandler.updateOrder(updateOrderCommand);
    }
}
