package mapper;


import java.util.List;

import org.springframework.stereotype.Component;

import dto.inputdto.CreateOrderCommand;
import dto.ouputdto.OrderCreatedResponse;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import valueobject.CustomerId;
import valueobject.Money;
import valueobject.ProductId;
import valueobject.RestaurantId;
import valueobject.StreetAddress;

@Component
public class OrderDataMapper {

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.customerId()))
                .restaurantId(new RestaurantId(createOrderCommand.restaurantId()))
                .price(new Money(createOrderCommand.price()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.address()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.items()))
                .build();
    }

    public OrderCreatedResponse orderToCreateOrderResponse(Order order) {
        return new OrderCreatedResponse(
            order.getTrackingId().getValue(),
            order.getOrderStatus()
        );
    }
    private StreetAddress orderAddressToStreetAddress(CreateOrderCommand.OrderAddress address) {
        return new StreetAddress(
                address.street(),
                address.postalCode(),
                address.city()
        );
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<CreateOrderCommand.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> 
                    OrderItem.builder()
                        .product(new Product(
                                new ProductId(orderItem.productId()),
                                null, // Name not available in command
                                new Money(orderItem.price())
                        ))
                        .price(new Money(orderItem.price()))
                        .quantity(orderItem.quantity())
                        .subTotal(new Money(orderItem.subTotal()))
                        .build()
                )
                .toList();
    }
}