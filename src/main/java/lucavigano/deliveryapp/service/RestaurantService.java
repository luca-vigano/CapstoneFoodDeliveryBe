package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.CreateRestaurantRequest;
import lucavigano.deliveryapp.DTO.RestaurantDTO;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);


    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest upndateRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

//    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDTO addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
