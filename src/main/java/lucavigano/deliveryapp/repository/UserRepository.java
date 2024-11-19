package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail (String username);
}
