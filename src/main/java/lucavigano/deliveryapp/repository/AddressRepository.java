package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
