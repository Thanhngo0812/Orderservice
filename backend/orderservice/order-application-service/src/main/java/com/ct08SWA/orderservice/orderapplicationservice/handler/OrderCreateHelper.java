package com.ct08SWA.orderservice.orderapplicationservice.handler;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.CreateOrderCommand;
import com.ct08SWA.orderservice.orderdomaincore.entity.Order;
import com.ct08SWA.orderservice.orderdomaincore.event.OrderCreatedEvent;
import jakarta.transaction.Transactional;
import com.ct08SWA.orderservice.orderapplicationservice.mapper.OrderDataMapper;
import com.ct08SWA.orderservice.orderapplicationservice.ports.outputports.OrderRepository;
import com.ct08SWA.orderservice.orderdomaincore.service.OrderDomainService;



@Component
public class OrderCreateHelper {

	private static final Logger log = LoggerFactory.getLogger(OrderCreateHelper.class);
    
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository; 
    private final OrderDataMapper orderDataMapper;

    public OrderCreateHelper(OrderDomainService orderDomainService,
                           OrderRepository orderRepository, // ✅ Interface, không phải implementation
                           OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order);
        Order savedOrder = orderRepository.save(order); 
        
//        if (savedOrder == null) {
//            log.error("Could not save order with id: {}", order.getId().getValue());
//            throw new OrderDomainException("Could not save order!");
//        }
//        
        log.info("Order is saved with id: {}", savedOrder.getId().getValue());
        return orderCreatedEvent;
    }
}