package rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.inputdto.CreateOrderCommand;
import dto.ouputdto.OrderCreatedResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ports.inputports.OrderApplicationService;



@RestController
@RequestMapping(value = "/orders")
@Slf4j
public class OrderController {
	private final OrderApplicationService orderApplicationService;
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
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
	}


