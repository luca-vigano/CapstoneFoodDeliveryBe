package lucavigano.deliveryapp.repository;

import lucavigano.deliveryapp.entities.User;

public interface UserServ {

    public User findUserByJwtToken(String token) throws Exception;
}
