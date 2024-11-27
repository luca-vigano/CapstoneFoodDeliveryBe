package lucavigano.deliveryapp.controllers;


import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public User getCurrentUserProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }
}

