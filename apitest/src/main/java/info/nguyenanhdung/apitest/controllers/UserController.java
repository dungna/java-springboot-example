package info.nguyenanhdung.apitest.controllers;

import info.nguyenanhdung.apitest.models.UserModel;
import info.nguyenanhdung.apitest.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    private UserService userService;

    // build create User REST API
    @PostMapping("")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        UserModel saveUserModel = userService.createUser(userModel);
        return new ResponseEntity<>(saveUserModel, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Long userId){
        UserModel userModel = userService.getUserById(userId);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    // Build Get All Users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userModels = userService.getAllUsers();
        return new ResponseEntity<>(userModels, HttpStatus.OK);
    }

    // Build Update User REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") Long userId,
                                                @RequestBody UserModel userModel){
        userModel.setId(userId);
        UserModel updatedUserModel = userService.updateUser(userModel);
        return new ResponseEntity<>(updatedUserModel, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
