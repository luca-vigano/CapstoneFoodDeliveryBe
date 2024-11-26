package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.UserDTO;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.exceptions.BadRequestException;
import lucavigano.deliveryapp.exceptions.NotFoundException;
import lucavigano.deliveryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptencoder;

    public User save(UserDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(dipendente -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso");
                }
        );
        User newUtente = new User(body.fullname(), body.email(), bcryptencoder.encode(body.password()), body.role());
        return this.userRepository.save(newUtente);
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User finByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("l'untente con la mail " + email + " non è stato trovato"));
    }

}
