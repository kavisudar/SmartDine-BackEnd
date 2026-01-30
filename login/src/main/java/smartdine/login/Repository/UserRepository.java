package smartdine.login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartdine.login.Model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<org.apache.catalina.User> findByUsername(String username);
}
