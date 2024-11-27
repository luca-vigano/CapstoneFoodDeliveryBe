package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.RestaurantDTO;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> searchRestaurant(@AuthenticationPrincipal User currentUser,
                                             @RequestParam String keyword) throws Exception {
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return restaurants;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAllRestaurant(@AuthenticationPrincipal User currentUser) throws Exception {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        return restaurants;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant findRestaurantById(@AuthenticationPrincipal User currentUser,
                                         @PathVariable Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return restaurant;
    }

    @PutMapping("/{id}/add-favorites")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantDTO addToFavorites(@AuthenticationPrincipal User currentUser,
                                        @PathVariable Long id) throws Exception {
        RestaurantDTO restaurantDTO = restaurantService.addToFavorites(id, currentUser);
        return restaurantDTO;
    }
}

