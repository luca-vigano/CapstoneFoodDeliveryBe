package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.entities.Category;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserServ userService;

    @PostMapping("/admin/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category,
                                   @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);
        Category createdCategory=categoryService.createCategory(category.getName(), user.getId());
        return createdCategory;
    }

    @GetMapping("/category/restaurant")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getRestaurantCategory(@RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);
        List<Category> categories =categoryService.findCategoryByRestaurantId(user.getId());
        return categories;
    }
}
