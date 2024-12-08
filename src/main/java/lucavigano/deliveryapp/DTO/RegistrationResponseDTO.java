package lucavigano.deliveryapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucavigano.deliveryapp.enums.USER_ROLE;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationResponseDTO {
    String email;
    String fullname;
    String passWord;
    USER_ROLE role;
    String token;

}
