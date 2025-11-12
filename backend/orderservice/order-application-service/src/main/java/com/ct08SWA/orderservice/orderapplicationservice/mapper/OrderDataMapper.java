package com.ct08SWA.orderservice.orderapplicationservice.mapper;


import java.util.List;

import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.UpdateOrderStatusResponse;
import org.springframework.stereotype.Component;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.CreateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.OrderCreatedResponse;
import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.UpdateOrderResponse;
import com.ct08SWA.orderservice.orderdomaincore.entity.Order;
import com.ct08SWA.orderservice.orderdomaincore.entity.OrderItem;
import com.ct08SWA.orderservice.orderdomaincore.entity.Product;
import com.ct08SWA.orderservice.orderdomaincore.valueobject.CustomerId;
import com.ct08SWA.orderservice.orderdomaincore.valueobject.Money;
import com.ct08SWA.orderservice.orderdomaincore.valueobject.ProductId;
import com.ct08SWA.orderservice.orderdomaincore.valueobject.RestaurantId;
import com.ct08SWA.orderservice.orderdomaincore.valueobject.StreetAddress;

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

    public List<OrderItem> updateCommandItemsToOrderItems(List<UpdateOrderCommand.OrderItemData> items) {
        return items.stream()
                .map(item -> OrderItem.builder()
                        .product(new Product(new ProductId(item.productId()), null, new Money(item.price())))
                        .price(new Money(item.price()))
                        .quantity(item.quantity())
                        .subTotal(new Money(item.subTotal()))
                        .build())
                .toList();
    }

    public UpdateOrderResponse orderToUpdateOrderResponse(Order order) {
        return new UpdateOrderResponse(
                order.getId().getValue(),
                order.getOrderStatus(),
                "Order updated successfully"
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

    public UpdateOrderStatusResponse orderToUpdateOrderStatusResponse(Order order) {
        return new UpdateOrderStatusResponse(
                order.getId().getValue(),
                order.getOrderStatus(),
                "Order status updated successfully"
        );
    }

}
