package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.config.JWT;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.UserServImp;
import lucavigano.deliveryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserServImp userServImp;
    @Autowired
    private JWT jwt;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String token) throws Exception {
       String jwt = token.replace("Bearer ", "").trim();
        User user = userServImp.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
