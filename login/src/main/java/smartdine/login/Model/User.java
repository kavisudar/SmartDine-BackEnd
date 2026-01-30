package smartdine.login.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import smartdine.login.Enum.Role;


@Entity
@Table(name = "users")
@Data
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Size(max = 10)
    private Long phone;
    @Email
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}

