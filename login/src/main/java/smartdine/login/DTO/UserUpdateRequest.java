package smartdine.login.DTO;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequest {

    private String name;
    private String email;

}
