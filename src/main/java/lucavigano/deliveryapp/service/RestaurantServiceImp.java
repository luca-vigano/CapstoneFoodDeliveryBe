package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.CreateRestaurantRequest;
import lucavigano.deliveryapp.DTO.RestaurantDTO;
import lucavigano.deliveryapp.entities.Address;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.repository.AddressRepository;
import lucavigano.deliveryapp.repository.RestaurantRepository;
import lucavigano.deliveryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address= addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null) {
            restaurant.setCuisineType(updateRestaurant.getCuisineType());
        }

        if(restaurant.getDescription()!=null) {
            restaurant.setDescription(updateRestaurant.getDescription());
        }

        if (restaurant.getName()!=null){
            restaurant.setName(updateRestaurant.getName());
        }



        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant= restaurantRepository.findById(id);
        if (restaurant.isEmpty()){
            throw new Exception("risorante con id" + id + " non trovato");
        }
        return restaurant.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant==null){
            throw new Exception("Nessun ristorante trovato con Owner id" + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDTO addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant=findRestaurantById(restaurantId);
        RestaurantDTO restaurantDTO=new RestaurantDTO();
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setImages(restaurant.getImages());
        restaurantDTO.setTitle(restaurant.getName());
        restaurantDTO.setId(restaurantId);

        boolean isFavorite=false;
        List<RestaurantDTO> favorites = user.getFavorites();
        for (RestaurantDTO favorite : favorites) {
            if (favorite.getId().equals(restaurantId)) {
                isFavorite=true;
                break;
            }
        }

        if (isFavorite){
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            favorites.add(restaurantDTO);
        }

        userRepository.save(user);
        return restaurantDTO;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
