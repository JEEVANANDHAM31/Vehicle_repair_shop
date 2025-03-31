package com.example.Project.Service;

import com.example.Project.Entity.User;
import com.example.Project.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public List<User> findByRole(String role) {
        return userRepo.findByRole(role);
    }

    public List<User> paginate(int page, int size, String sortBy) {
        return userRepo.findAll(PageRequest.of(page, size, Sort.by(sortBy))).getContent();
    }

    public String addUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            return "Email already exists";
        }
        if (userRepo.existsByPhone(Long.valueOf(user.getPhone()))) {
            return "Phone number already exists";
        }
        userRepo.save(user);
        return "User successfully added";
    }

    public String updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepo.findById(id);
        if (existingUserOpt.isEmpty()) {
            return "User not found";
        }

        User existingUser = existingUserOpt.get();

        // Check for duplicate email (excluding current user)
        if (userRepo.existsByEmail(updatedUser.getEmail()) &&
            !existingUser.getEmail().equals(updatedUser.getEmail())) {
            return "Email already exists";
        }

        // Check for duplicate phone number (excluding current user)
        if (userRepo.existsByPhone(updatedUser.getPhone()) &&
            existingUser.getPhone() != updatedUser.getPhone()) {
            return "Phone number already exists";
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setRole(updatedUser.getRole());
        // Add other fields as necessary

        userRepo.save(existingUser);
        return "User updated successfully";
    }

    public String deleteUserById(Long id) {
        if (!userRepo.existsById(id)) {
            return "User not found with ID: " + id;
        }
        userRepo.deleteById(id);
        return "Deleted successfully";
    }

    public String deleteAllUsers() {
        userRepo.deleteAll();
        return "Deleted all users";
    }

    public User createUser(User user) {
        
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Transactional(readOnly = true)
    public User getUserWithAppointments(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getAppointments().size(); // Initialize lazy-loaded collection
        return user;
    }
}
