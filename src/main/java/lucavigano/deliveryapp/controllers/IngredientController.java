package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.IngredientCategoryRequest;
import lucavigano.deliveryapp.DTO.IngredientRequest;
import lucavigano.deliveryapp.entities.IngredientCategory;
import lucavigano.deliveryapp.entities.IngredientsItem;
import lucavigano.deliveryapp.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientCategory createIngredientCategory(
            @RequestBody IngredientCategoryRequest request
            ) throws Exception {
        IngredientCategory item=ingredientService.createIngredientCategory(request.getName(),request.getRestaurantId());
        return item;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientsItem createIngredientItem(
            @RequestBody IngredientRequest request
    ) throws Exception {
        IngredientsItem item=ingredientService.createIngredientItem(request.getRestaurantId(),request.getName(), request.getCategoryId());
        return item;
    }

    @PutMapping("/{id}/stock")
    @ResponseStatus(HttpStatus.OK)
    public IngredientsItem updateIngredientStock(
            @PathVariable Long id
    ) throws Exception {
        IngredientsItem item=ingredientService.updateStock(id);
        return item;
    }

    @GetMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientsItem> getRestaurantIngredient(
            @PathVariable Long id
    ) throws Exception {
       List <IngredientsItem> item=ingredientService.findRestauranstIngredients(id);
        return item;
    }

    @GetMapping("/restaurant/{id}/category")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientCategory> getRestaurantIngredientCategory(
            @PathVariable Long id
    ) throws Exception {
        List <IngredientCategory> item=ingredientService.findIngredientCategoryByRestaurantId(id);
        return item;
    }
}
