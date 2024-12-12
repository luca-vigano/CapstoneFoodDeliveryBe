package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.Category;
import lucavigano.deliveryapp.entities.Food;
import lucavigano.deliveryapp.entities.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {


    List<Food> findByIngredientsContaining(IngredientsItem ingredient);
    List<Food> findByFoodCategory(Category category);


    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);
}
