package com.leozeballos.apirestspringboot.repository;

import com.leozeballos.apirestspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsernameOrEmail(String username, String email);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
