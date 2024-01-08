package info.nguyenanhdung.apitest.services;

import info.nguyenanhdung.apitest.models.UserModel;

import info.nguyenanhdung.apitest.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private IUserRepository IUserRepository;

    @Override
    public UserModel createUser(UserModel userModel) {
        return IUserRepository.save(userModel);
    }

    @Override
    public UserModel getUserById(Long userId) {
        Optional<UserModel> optionalUser = IUserRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<UserModel> getAllUsers() {
        return IUserRepository.findAll();
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        UserModel existingUserModel = IUserRepository.findById(userModel.getId()).get();
        existingUserModel.setFirstName(userModel.getFirstName());
        existingUserModel.setLastName(userModel.getLastName());
        existingUserModel.setEmail(userModel.getEmail());
        UserModel updatedUserModel = IUserRepository.save(existingUserModel);
        return updatedUserModel;
    }

    @Override
    public void deleteUser(Long userId) {
        IUserRepository.deleteById(userId);
    }
}
