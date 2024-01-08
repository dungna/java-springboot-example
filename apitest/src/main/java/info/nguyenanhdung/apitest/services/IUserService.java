package info.nguyenanhdung.apitest.services;

import info.nguyenanhdung.apitest.models.UserModel;

import java.util.List;

public interface IUserService {
    UserModel createUser(UserModel userModel);

    UserModel getUserById(Long userId);

    List<UserModel> getAllUsers();

    UserModel updateUser(UserModel userModel);

    void deleteUser(Long userId);
}
