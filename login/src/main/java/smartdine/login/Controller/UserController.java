package smartdine.login.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import smartdine.login.DTO.UserUpdateRequest;
import smartdine.login.Service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @Valid @RequestBody UserUpdateRequest request) {

        return ResponseEntity.ok(userService.updateProfile(request));
    }
}
