package smartdine.login.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import smartdine.login.Enum.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegisterRequest {
    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 30, message = "Username must be between 4 and 30 characters")
    private String username;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10,12}$",
            message = "Phone number must contain only digits and be 10–12 characters long"
    )
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Address is required")
    private String address;
}
