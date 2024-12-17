package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.CreateRestaurantRequest;
import lucavigano.deliveryapp.DTO.MessageResponse;
import lucavigano.deliveryapp.entities.Event;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.EventService;
import lucavigano.deliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private EventService eventService;

    @PostMapping("/restaurants")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody CreateRestaurantRequest req,
                                       @AuthenticationPrincipal User currentUser) throws Exception {
        Restaurant restaurant = restaurantService.createRestaurant(req, currentUser);
        return restaurant;
    }

    @PutMapping("/restaurants/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                       @PathVariable Long id,
                                       @AuthenticationPrincipal User currentUser) throws Exception {
        Restaurant restaurant = restaurantService.updateRestaurant(id, req);
        return restaurant;
    }

    @DeleteMapping("/restaurants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteRestaurant(@PathVariable Long id,
                                            @AuthenticationPrincipal User currentUser) throws Exception {
        restaurantService.deleteRestaurant(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Ristorante cancellato correttamente!");
        return messageResponse;
    }

    @PutMapping("/restaurants/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant updateRestaurantStatus(@PathVariable Long id,
                                             @AuthenticationPrincipal User currentUser) throws Exception {
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
        return restaurant;
    }

    @GetMapping("/restaurants/user")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant findRestaurantByUserId(@AuthenticationPrincipal User currentUser) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(currentUser.getId());
        return restaurant;
    }

    @PostMapping("/events/restaurants/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event,
                             @PathVariable Long restaurantId
                            ) throws Exception {
        return eventService.createEvent(restaurantId, event);
    }

    @DeleteMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long eventId) throws Exception {
        eventService.deleteEvent(eventId);
    }

    @GetMapping("/events/restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getRestaurantEvents(@PathVariable Long restaurantId) throws Exception {
        return eventService.getEventsByRestaurantId(restaurantId);
    }
}
