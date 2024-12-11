package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.Address;
import lucavigano.deliveryapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
}
