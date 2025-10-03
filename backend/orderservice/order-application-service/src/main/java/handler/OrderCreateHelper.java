package handler;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import dto.inputdto.CreateOrderCommand;
import entity.Order;
import event.OrderCreatedEvent;
import jakarta.transaction.Transactional;
import mapper.OrderDataMapper;
import ports.outputports.OrderRepository;
import service.OrderDomainService;



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