package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.entities.Category;
import lucavigano.deliveryapp.entities.Food;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.repository.CategoryRepository;
import lucavigano.deliveryapp.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FoodRepository foodRepository;


    public Category createCategory(String name, Long userId) throws Exception{

        Restaurant restaurant=restaurantService.getRestaurantByUserId(userId);
        Category category=new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }


    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
//        Restaurant restaurant=restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(id);
    }


    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category=categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new Exception("Categoria non trovata");
        }
        return category.get();
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Trovare tutti i cibi associati a questa categoria
        List<Food> foods = foodRepository.findByFoodCategory(category);

        // Eliminare i cibi associati
        foodRepository.deleteAll(foods);

        // Eliminare la categoria
        categoryRepository.delete(category);
    }
}
