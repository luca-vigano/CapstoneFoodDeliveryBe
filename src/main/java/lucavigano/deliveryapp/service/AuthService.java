package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.UserLoginDTO;
import lucavigano.deliveryapp.config.JWT;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String checkCredenzialiAndToken(UserLoginDTO body){

        User userFound = this.userService.finByEmail(body.email());
        if (bcryptencoder.matches(body.password(), userFound.getPassword())){
            String accessToken = jwt.createToken(userFound);
            return accessToken;
        }else {
            throw new UnauthorizedException("credenziali inserite errate");
        }
    }
}
