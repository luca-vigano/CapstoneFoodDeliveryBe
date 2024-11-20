package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.UserLoginDTO;
import lucavigano.deliveryapp.DTO.UserLoginResponseDTO;
import lucavigano.deliveryapp.config.JWT;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder bcryptencoder;

    public UserLoginResponseDTO checkCredenzialiAndToken(UserLoginDTO body){

        User userFound = this.userService.finByEmail(body.email());
        if (bcryptencoder.matches(body.password(), userFound.getPassword())){
            String accessToken = jwt.createToken(userFound);

            UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
            userLoginResponseDTO.setToken(accessToken);
            userLoginResponseDTO.setMessage("Benvenuto!");
            userLoginResponseDTO.setRole(userFound.getRole());
            return userLoginResponseDTO;
        }else {
            throw new UnauthorizedException("credenziali inserite errate");
        }
    }
}
