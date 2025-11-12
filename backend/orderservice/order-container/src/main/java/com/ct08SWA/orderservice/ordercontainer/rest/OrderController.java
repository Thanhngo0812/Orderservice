package com.ct08SWA.orderservice.ordercontainer.rest;

import java.util.List;
import java.util.UUID;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderStatusCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.UpdateOrderStatusResponse;
import com.ct08SWA.orderservice.orderdomaincore.valueobject.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.CreateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.inputdto.UpdateOrderCommand;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.OrderCreatedResponse;
import com.ct08SWA.orderservice.orderapplicationservice.dto.ouputdto.UpdateOrderResponse;
import jakarta.validation.Valid;
import com.ct08SWA.orderservice.orderapplicationservice.ports.inputports.OrderApplicationService;



@RestController
@RequestMapping(value = "/orders")
@Slf4j
public class OrderController {
	private final OrderApplicationService orderApplicationService;
	private static final Logger log = LoggerFactory.getLogger(OrderController.class)
	public OrderController(OrderApplicationService orderApplicationService) {
		this.orderApplicationService = orderApplicationService;
		}
	//new order
	@PostMapping
	public ResponseEntity<OrderCreatedResponse> createOrder(
	@Valid @RequestBody CreateOrderCommand createOrderCommand) {
	// Log request
	log.info("Creating order for customer: {} at restaurant: {}",
	createOrderCommand.customerId(),
	createOrderCommand.restaurantId());
	// Delegate to use case (Input Port)
	OrderCreatedResponse response =
	orderApplicationService.createOrder(createOrderCommand);
	// Log success
	log.info("Order created with tracking id: {}",
	response.orderTrackingId());
	// Return HTTP 201 Created
	return ResponseEntity
	.status(HttpStatus.CREATED)
	.body(response);
	}

	// update order items when PENDING
	@PutMapping("/{orderId}")
	public ResponseEntity<UpdateOrderResponse> updateOrder(
		@PathVariable java.util.UUID orderId,
		@Valid @RequestBody UpdateOrderCommand updateOrderCommand) {
		if (!orderId.equals(updateOrderCommand.orderId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new UpdateOrderResponse(null, null, "Path orderId does not match body orderId"));
		}
		log.info("Updating order: {}", orderId);
		UpdateOrderResponse response = orderApplicationService.updateOrder(updateOrderCommand);
		return ResponseEntity.ok(response);
	}
    @PutMapping("/{orderId}/status")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(
            @PathVariable UUID orderId,
            @RequestBody UpdateOrderStatusCommand command) {
        if (!orderId.equals(command.orderId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UpdateOrderStatusResponse(null, null,
                            "Path orderId does not match body orderId"));
        }

        // Call Application Service
        UpdateOrderStatusResponse response = orderApplicationService.updateOrderStatus(command);
        return ResponseEntity.ok(response);
    }
}


