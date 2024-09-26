package com.example.HotelServer.repository;

import com.example.HotelServer.entity.User;
import com.example.HotelServer.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}
