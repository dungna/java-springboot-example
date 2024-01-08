package info.nguyenanhdung.apitest.service;

import info.nguyenanhdung.apitest.entity.User;
import java.util.List;

public interface IUserService {
    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
