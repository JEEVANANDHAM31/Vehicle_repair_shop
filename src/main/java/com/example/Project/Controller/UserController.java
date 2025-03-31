package com.example.Project.Controller;

import com.example.Project.Entity.User;
import com.example.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/pagination")
    public List<User> paginate(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        return userService.paginate(page, size, sortBy);
    }

    @GetMapping("/getby")
    public List<User> findByRole(@RequestParam String role) {
        return userService.findByRole(role);
    }

    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    } // Closing brace added here

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @DeleteMapping("/deleteall")
    public String deleteAllUsers() {
        return userService.deleteAllUsers();
    }
}