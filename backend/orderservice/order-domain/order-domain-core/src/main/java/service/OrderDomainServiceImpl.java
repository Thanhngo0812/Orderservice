package service;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import entity.Order;
import event.OrderCreatedEvent;

@Service
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order) {
        order.initializeOrder();
        order.validateOrder();
        return new OrderCreatedEvent(order, ZonedDateTime.now());
    }

    @Override
    public void payOrder(Order order) {
        order.pay();
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
    }

    @Override
    public void cancelOrder(Order order, java.util.List<String> failureMessages) {
        order.cancel(failureMessages);
    }

    @Override
    public void initiateCancel(Order order, java.util.List<String> failureMessages) {
        order.initCancel(failureMessages);
    }
}