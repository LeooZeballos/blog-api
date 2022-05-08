package com.leozeballos.apirestspringboot.repository;

import com.leozeballos.apirestspringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRolByName(String name);
}
