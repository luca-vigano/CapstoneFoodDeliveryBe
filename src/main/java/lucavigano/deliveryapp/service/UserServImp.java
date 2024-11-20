package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.config.JWT;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.repository.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServImp implements UserServ {

    @Autowired
    private UserService userService;
    @Autowired
    private JWT jwt;

    @Override
    public User findUserByJwtToken(String token) throws Exception {
        String email = jwt.getEmailFromToken(token);
        User user = userService.finByEmail(email);

        return user;
    }
}
