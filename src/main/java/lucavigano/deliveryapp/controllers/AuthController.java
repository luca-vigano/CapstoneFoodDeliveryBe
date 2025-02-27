package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.RegistrationResponseDTO;
import lucavigano.deliveryapp.DTO.UserDTO;
import lucavigano.deliveryapp.DTO.UserLoginDTO;
import lucavigano.deliveryapp.DTO.UserLoginResponseDTO;
import lucavigano.deliveryapp.config.JWT;
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
    @Autowired
    JWT jwt;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDTO body) {
        UserLoginResponseDTO userLoginResponseDTO = this.authService.checkCredenzialiAndToken(body);
        return ResponseEntity.ok(userLoginResponseDTO);
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponseDTO save(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        User savedUser = this.userService.save(body);
        User userFound = this.userService.finByEmail(body.email());
        RegistrationResponseDTO registrationResponseDTO=new RegistrationResponseDTO();
        String accessToken= jwt.createToken(userFound);
        registrationResponseDTO.setFullname(userFound.getFullName());
        registrationResponseDTO.setEmail(userFound.getEmail());
        registrationResponseDTO.setPassWord(userFound.getPassword());
        registrationResponseDTO.setRole(userFound.getRole());
        registrationResponseDTO.setToken(accessToken);

        System.out.println("User data received: " + body);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);



        return registrationResponseDTO;
    }
}
