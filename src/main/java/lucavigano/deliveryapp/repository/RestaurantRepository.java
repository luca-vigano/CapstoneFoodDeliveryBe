package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    @Query(value = "SELECT * FROM restaurant r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(r.cuisine_type) LIKE LOWER(CONCAT('%', :query, '%'))",
            nativeQuery = true)
    List<Restaurant> findBySearchQuery(String query);


    Restaurant findByOwnerId(Long userId);

    }
