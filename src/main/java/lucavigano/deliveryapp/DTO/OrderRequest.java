package lucavigano.deliveryapp.DTO;

import lombok.Data;
import lucavigano.deliveryapp.entities.Address;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;
}
