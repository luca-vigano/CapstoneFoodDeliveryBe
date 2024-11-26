package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.CreateRestaurantRequest;
import lucavigano.deliveryapp.DTO.MessageResponse;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserServ userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader ("Authorization") String token) throws Exception{
        String jwt = token.replace("Bearer ", "").trim();
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant= restaurantService.createRestaurant(req,user);
        return restaurant;
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                       @RequestHeader ("Authorization") String jwt,
                                       @PathVariable Long id) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant= restaurantService.updateRestaurant(id, req);
        return restaurant;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteRestaurant(@RequestHeader ("Authorization") String jwt,
                                       @PathVariable Long id) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("Ristorante cancellato correttamente!");

        return messageResponse;
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant updateRestaurantStatus(@RequestHeader ("Authorization") String token,
                                            @PathVariable Long id) throws Exception{
        String jwt = token.replace("Bearer ", "").trim();
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant= restaurantService.updateRestaurantStatus(id);
        return restaurant;
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant findRestaurantByUserId(@RequestHeader ("Authorization") String token) throws Exception{
        String jwt = token.replace("Bearer ", "").trim();
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant= restaurantService.getRestaurantByUserId(user.getId());
        return restaurant;
    }

}
