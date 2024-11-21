package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.CreateFoodRequest;
import lucavigano.deliveryapp.entities.Food;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.repository.UserServ;
import lucavigano.deliveryapp.service.FoodService;
import lucavigano.deliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private UserServ userService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.CREATED)
    public List <Food> searchFood(@RequestParam String name,
                           @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);

        List<Food> food=foodService.searchFood(name);
        return food;
    }

    @GetMapping("/restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    public List <Food> getRestaurantFood(
                                    @RequestParam boolean vegetarian,
                                    @RequestParam boolean nonvegetarian,
                                    @RequestParam(required = false) String food_category,
                                    @PathVariable Long restaurantId,
                                    @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);

        List<Food> food=foodService.getRestaurantsFood(restaurantId,vegetarian,nonvegetarian,food_category);
        return food;
    }
}
