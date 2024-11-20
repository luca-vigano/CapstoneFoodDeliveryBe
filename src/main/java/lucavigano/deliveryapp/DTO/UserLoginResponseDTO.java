package lucavigano.deliveryapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucavigano.deliveryapp.enums.USER_ROLE;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginResponseDTO {
    
    String token;
    String message;
    USER_ROLE role;

    public UserLoginResponseDTO(UserLoginResponseDTO userLoginResponseDTO) {
    }

//    public UserLoginResponseDTO(ResponseEntity<UserLoginResponseDTO> userLoginResponseDTOResponseEntity) {
//    }
}
