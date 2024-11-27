package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.entities.Category;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category,
                                   @AuthenticationPrincipal User currentUser) throws Exception {
        Category createdCategory = categoryService.createCategory(category.getName(), currentUser.getId());
        return createdCategory;
    }

    @GetMapping("/category/restaurant")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getRestaurantCategory(@AuthenticationPrincipal User currentUser) throws Exception {
        List<Category> categories = categoryService.findCategoryByRestaurantId(currentUser.getId());
        return categories;
    }
}

