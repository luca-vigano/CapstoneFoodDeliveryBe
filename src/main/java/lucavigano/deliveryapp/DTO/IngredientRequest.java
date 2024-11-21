package lucavigano.deliveryapp.DTO;

import lombok.Data;

@Data
public class IngredientRequest {
    private String name;
    private Long categotyId;
    private Long restaurantId;
}
