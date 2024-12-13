package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.entities.Food;
import lucavigano.deliveryapp.entities.IngredientCategory;
import lucavigano.deliveryapp.entities.IngredientsItem;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.repository.FoodRepository;
import lucavigano.deliveryapp.repository.IngredientCategoryRepository;
import lucavigano.deliveryapp.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private FoodRepository foodRepository;



    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category=new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);
    }


    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory=ingredientCategoryRepository.findById(id);

        if (ingredientCategory.isEmpty()){
            throw new Exception("Categoria non trovata");
        }
        return ingredientCategory.get();
    }


    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }


    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category =findIngredientCategoryById(categoryId);

        IngredientsItem item =new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem ingredient=ingredientItemRepository.save(item);
        category.getIngredients().add(ingredient);
        return ingredient;
    }


    public List<IngredientsItem> findRestauranstIngredients(Long restaurantId) throws Exception {

        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }


    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem=ingredientItemRepository.findById(id);
        if (optionalIngredientsItem.isEmpty()){
            throw new Exception("ingrediente non trovato");
        }
        IngredientsItem ingredientsItem=optionalIngredientsItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientItemRepository.save(ingredientsItem);
    }

    public void deleteIngredientCategory(Long id) throws Exception {
        // Recupera la categoria
        IngredientCategory category = ingredientCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("IngredientCategory not found with ID: " + id));

        // Trova e elimina tutti gli ingredient associati
        for (IngredientsItem ingredient : category.getIngredients()) {
            deleteIngredientItem(ingredient.getId());
        }

        // Elimina la categoria
        ingredientCategoryRepository.delete(category);
    }

    // Elimina un IngredientsItem e tutti i Food associati
    public void deleteIngredientItem(Long id) throws Exception {
        // Recupera l'ingredient
        IngredientsItem ingredient = ingredientItemRepository.findById(id)
                .orElseThrow(() -> new Exception("IngredientsItem not found with ID: " + id));

        // Trova e elimina tutti i Food associati
        List<Food> foodsWithIngredient = foodRepository.findByIngredientsContaining(ingredient);
        for (Food food : foodsWithIngredient) {
            foodRepository.delete(food);
        }

        // Elimina l'IngredientsItem
        ingredientItemRepository.delete(ingredient);
    }

}
