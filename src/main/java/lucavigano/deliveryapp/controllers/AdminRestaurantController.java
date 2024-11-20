package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.CreateRestaurantRequest;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.exceptions.BadRequestException;
import lucavigano.deliveryapp.service.RestaurantService;
import lucavigano.deliveryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody @Validated CreateRestaurantRequest req,BindingResult validationResult){
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Errore nel payload!");
        }


        return null;
    }
}
