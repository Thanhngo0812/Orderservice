package adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import entity.Order;
import mapper.OrderDataAccessMapper;
import ports.outputports.OrderRepository;
import repository.OrderJpaRepository;
import valueobject.OrderId;
import valueobject.TrackingId;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository, 
                             OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order save(Order order) {
        var orderEntity = orderDataAccessMapper.orderToOrderEntity(order);
        var savedOrderEntity = orderJpaRepository.save(orderEntity);
        return orderDataAccessMapper.orderEntityToOrder(savedOrderEntity);
    }

	@Override
	public Optional<Order> findById(OrderId orderId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Order> findByTrackingId(TrackingId trackingId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}