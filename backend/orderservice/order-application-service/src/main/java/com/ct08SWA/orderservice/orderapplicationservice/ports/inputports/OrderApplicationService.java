package com.ct08SWA.orderservice.orderapplicationservice.ports.inputports;
import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.CreateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.OrderCreatedResponse;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.UpdateOrderResponse;

public interface OrderApplicationService {
	OrderCreatedResponse createOrder(CreateOrderCommand createOrderCommand);

	UpdateOrderResponse updateOrder(UpdateOrderCommand updateOrderCommand);
}
