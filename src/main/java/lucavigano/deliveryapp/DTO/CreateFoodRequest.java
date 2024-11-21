package lucavigano.deliveryapp.DTO;

import lombok.Data;
import lucavigano.deliveryapp.entities.Category;
import lucavigano.deliveryapp.entities.IngredientsItem;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegetarian;
    private List<IngredientsItem> ingredients;
}
