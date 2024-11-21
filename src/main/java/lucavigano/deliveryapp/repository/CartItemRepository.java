package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
