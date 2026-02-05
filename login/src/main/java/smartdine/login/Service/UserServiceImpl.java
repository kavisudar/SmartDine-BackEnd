package smartdine.login.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smartdine.login.DTO.LoginRequest;
import smartdine.login.DTO.LoginResponse;
import smartdine.login.DTO.RegisterRequest;
import smartdine.login.DTO.UserUpdateRequest;
import smartdine.login.Enum.Role;
import smartdine.login.Model.User;
import smartdine.login.Repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* ===================== REGISTER USER ===================== */

    @Override
    public String register(RegisterRequest request) {

        // Check username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Check email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Map DTO → Entity
        User user = new User();
        user.setFullname(request.getFullName());
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setRole(Role.USER);

        userRepository.save(user);

        return "User registered successfully";
    }

    /* ===================== GET PROFILE ===================== */

    @Override
    public User getProfile() {
        return getCurrentUser();
    }

    /* ===================== UPDATE PROFILE ===================== */

    @Override
    public User updateProfile(UserUpdateRequest request) {

        User user = getCurrentUser();

        // Check email uniqueness if changed
        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setFullname(request.getName());
        user.setEmail(request.getEmail());
//        user.setPhone(request.getPhone());

        return userRepository.save(user);
    }

    /* ===================== HELPER METHOD ===================== */

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Unauthenticated access");
        }

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public LoginResponse login(LoginRequest request) {


        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new LoginResponse("Login successful", user.get().getRole().name());
    }
}
