package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.entities.Food;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Food> searchFood(@RequestParam String name,
                                 @AuthenticationPrincipal User currentUser) throws Exception {
        List<Food> food = foodService.searchFood(name);
        return food;
    }

    @GetMapping("/restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Food> getRestaurantFood(
            @RequestParam(required = false) boolean vegetarian,
            @RequestParam(required = false) boolean nonvegetarian,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @AuthenticationPrincipal User currentUser) throws Exception {
        List<Food> food = foodService.getRestaurantsFood(restaurantId, vegetarian, nonvegetarian, food_category);
        return food;
    }
}

