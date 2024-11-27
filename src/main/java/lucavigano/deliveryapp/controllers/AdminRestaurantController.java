package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.CreateRestaurantRequest;
import lucavigano.deliveryapp.DTO.MessageResponse;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody CreateRestaurantRequest req,
                                       @AuthenticationPrincipal User currentUser) throws Exception {
        Restaurant restaurant = restaurantService.createRestaurant(req, currentUser);
        return restaurant;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                       @PathVariable Long id,
                                       @AuthenticationPrincipal User currentUser) throws Exception {
        Restaurant restaurant = restaurantService.updateRestaurant(id, req);
        return restaurant;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteRestaurant(@PathVariable Long id,
                                            @AuthenticationPrincipal User currentUser) throws Exception {
        restaurantService.deleteRestaurant(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Ristorante cancellato correttamente!");
        return messageResponse;
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant updateRestaurantStatus(@PathVariable Long id,
                                             @AuthenticationPrincipal User currentUser) throws Exception {
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
        return restaurant;
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant findRestaurantByUserId(@AuthenticationPrincipal User currentUser) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(currentUser.getId());
        return restaurant;
    }
}
