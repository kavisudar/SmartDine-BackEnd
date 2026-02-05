package smartdine.login.DTO;

import lombok.Data;

@Data
public class LoginResponse {
    private long id;
    private String username;
    private String role;

    public LoginResponse(String loginSuccessful, String name) {
    }
}
