package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.RestaurantDTO;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.repository.UserServ;
import lucavigano.deliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserServ userService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List <Restaurant> searchRestaurant(@RequestHeader ("Authorization") String token,
                                            @RequestParam String keyword) throws Exception{
        String jwt = token.replace("Bearer ", "").trim();
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant= restaurantService.searchRestaurant(keyword);
        return restaurant;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List <Restaurant> getAllRestaurant(@RequestHeader ("Authorization") String token) throws Exception{
        String jwt = token.replace("Bearer ", "").trim();
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant= restaurantService.getAllRestaurant();
        return restaurant;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant findRestaurantById(@RequestHeader ("Authorization") String jwt,
                                                @PathVariable Long id) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant= restaurantService.findRestaurantById(id);
        return restaurant;
    }

    @PutMapping("/{id}/add-favorites")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantDTO addToFavorites(@RequestHeader ("Authorization") String token,
                                         @PathVariable Long id) throws Exception{
        String jwt = token.replace("Bearer ", "").trim();
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDTO restaurantDTO= restaurantService.addToFavorites(id,user);
        return restaurantDTO;
    }
}
