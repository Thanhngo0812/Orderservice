package ports.outputports;

import java.util.Optional;

import entity.Order;
import valueobject.OrderId;
import valueobject.TrackingId;

public interface OrderRepository {
    
    /**
     * Save order to database
     * @param order the order to save
     * @return the saved order
     */
    Order save(Order order);
    
    /**
     * Find order by ID
     * @param orderId the order ID
     * @return Optional of order if found
     */
    Optional<Order> findById(OrderId orderId);
    
    /**
     * Find order by tracking ID
     * @param trackingId the tracking ID
     * @return Optional of order if found
     */
    Optional<Order> findByTrackingId(TrackingId trackingId);
}