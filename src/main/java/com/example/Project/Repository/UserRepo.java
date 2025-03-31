package com.example.Project.Repository;

import com.example.Project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(long phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(long phone);
    List<User> findByRole(String role);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> getUsersByRole(@Param("role") String role);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    long countUsersByRole(@Param("role") String role);
}
