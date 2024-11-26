package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
