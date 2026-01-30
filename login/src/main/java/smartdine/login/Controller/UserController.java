package smartdine.login.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import smartdine.login.DTO.UserUpdateRequest;
import smartdine.login.Service.UserService;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userService.updateProfile(request));
    }

    @GetMapping("/demo")
    public String getData()
    {
        return "Hi";
    }



}
