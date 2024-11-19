package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
