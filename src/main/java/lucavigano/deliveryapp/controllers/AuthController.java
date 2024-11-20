package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.UserDTO;
import lucavigano.deliveryapp.DTO.UserLoginDTO;
import lucavigano.deliveryapp.DTO.UserLoginResponseDTO;
import lucavigano.deliveryapp.entities.Cart;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.exceptions.BadRequestException;
import lucavigano.deliveryapp.repository.CartRepository;
import lucavigano.deliveryapp.service.AuthService;
import lucavigano.deliveryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDTO body) {
        UserLoginResponseDTO userLoginResponseDTO = this.authService.checkCredenzialiAndToken(body);
        return ResponseEntity.ok(userLoginResponseDTO);
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        User savedUser = this.userService.save(body);

        Cart cart = new Cart();
        cart.setCutomer(savedUser);
        cartRepository.save(cart);



        return savedUser;
    }
}
