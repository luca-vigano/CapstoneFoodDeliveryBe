package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.exceptions.NotFoundException;
import lucavigano.deliveryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptencoder;

//    public User save(UtenteDTO body) {
//        this.utenteRepository.findByEmail(body.email()).ifPresent(dipendente -> {
//                    throw new BadRequestException("Email " + body.email() + " già in uso");
//                }
//        );
//        Utente newUtente = new Utente(body.username(), body.email(), bcryptencoder.encode(body.password()), body.nome(), body.cognome());
//        newUtente.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
//        return this.utenteRepository.save(newUtente);
//    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

}
