package smartdine.login.Service;

import smartdine.login.DTO.LoginRequest;
import smartdine.login.DTO.LoginResponse;
import smartdine.login.DTO.RegisterRequest;
import smartdine.login.DTO.UserUpdateRequest;
import smartdine.login.Model.User;

public interface UserService {
    smartdine.login.Model.User getProfile();

    User updateProfile(UserUpdateRequest request);

    String register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
