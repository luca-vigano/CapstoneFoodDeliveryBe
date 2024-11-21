package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.CreateFoodRequest;
import lucavigano.deliveryapp.DTO.MessageResponse;
import lucavigano.deliveryapp.entities.Food;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.repository.UserServ;
import lucavigano.deliveryapp.service.FoodService;
import lucavigano.deliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserServ userService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Food createFood(@RequestBody CreateFoodRequest request,
                           @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.findRestaurantById(request.getRestaurantId());
        Food food=foodService.createFood(request,request.getCategory(),restaurant);
        return food;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse deleteFood(@PathVariable Long id,
                                      @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("Ingrediente cancellato con successo");
        return messageResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Food updateFoodAvailabilityStatus(@PathVariable Long id,
                                      @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);
        Food food =foodService.updateAvailabilityStatus(id);

        return food;
    }
}
