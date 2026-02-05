package smartdine.login.Model;

import jakarta.persistence.*;
import lombok.Data;
import smartdine.login.Enum.Role;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Fullname;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

//    @Column(length = 10)
    private String phone;

    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
}
