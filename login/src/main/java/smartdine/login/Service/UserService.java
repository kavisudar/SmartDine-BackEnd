package smartdine.login.Service;

import smartdine.login.DTO.UserUpdateRequest;
import smartdine.login.Model.User;

public interface UserService {
    smartdine.login.Model.User getProfile();

    User updateProfile(UserUpdateRequest request);
}
