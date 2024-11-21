package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.CreateFoodRequest;
import lucavigano.deliveryapp.entities.Category;
import lucavigano.deliveryapp.entities.Food;
import lucavigano.deliveryapp.entities.Restaurant;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian,boolean isNotVegetarian,String foodCategory);

    public List<Food> searchFood(String keyword);
    public Food findFoodById (long foodId) throws Exception;
    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
