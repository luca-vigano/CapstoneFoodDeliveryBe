package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.entities.IngredientCategory;
import lucavigano.deliveryapp.entities.IngredientsItem;

import java.util.List;

public interface IngredientService {

    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName,Long categoryId) throws Exception;

    public List<IngredientsItem> findRestauranstIngredients(Long restaurantId) throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;
}
