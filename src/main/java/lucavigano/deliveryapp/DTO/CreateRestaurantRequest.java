package lucavigano.deliveryapp.DTO;

import lombok.Data;
import lucavigano.deliveryapp.entities.Address;
import lucavigano.deliveryapp.entities.ContactInformation;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String>images;
}
