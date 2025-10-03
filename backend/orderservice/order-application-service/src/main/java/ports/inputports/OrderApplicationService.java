package ports.inputports;
import dto.inputdto.CreateOrderCommand;
import dto.ouputdto.OrderCreatedResponse;

public interface OrderApplicationService {
	OrderCreatedResponse createOrder(CreateOrderCommand createOrderCommand);
}
