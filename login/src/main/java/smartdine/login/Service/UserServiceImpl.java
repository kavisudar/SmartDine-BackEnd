package smartdine.login.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import smartdine.login.DTO.UserUpdateRequest;
import smartdine.login.Model.User;
import smartdine.login.Repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getProfile() {
        return getCurrentUser();
    }

    @Override
    public User updateProfile(UserUpdateRequest request) {
        User user = getCurrentUser();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

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

        return (User) userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username)
                );
    }
}
