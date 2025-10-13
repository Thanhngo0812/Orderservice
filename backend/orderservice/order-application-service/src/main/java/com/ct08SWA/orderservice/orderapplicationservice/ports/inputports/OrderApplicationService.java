package com.ct08SWA.orderservice.orderapplicationservice.ports.inputports;
import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.CreateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.OrderCreatedResponse;

public interface OrderApplicationService {
	OrderCreatedResponse createOrder(CreateOrderCommand createOrderCommand);
}
