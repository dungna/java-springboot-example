package info.nguyenanhdung.apitest.service;

import info.nguyenanhdung.apitest.entity.User;

import info.nguyenanhdung.apitest.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private IUserRepository IUserRepository;

    @Override
    public User createUser(User user) {
        return IUserRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = IUserRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return IUserRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = IUserRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = IUserRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        IUserRepository.deleteById(userId);
    }
}
