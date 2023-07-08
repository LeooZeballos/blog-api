package com.leozeballos.blog.service;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leozeballos.blog.dto.LoginDTO;
import com.leozeballos.blog.dto.RegisterDTO;
import com.leozeballos.blog.entity.Role;
import com.leozeballos.blog.entity.User;
import com.leozeballos.blog.repository.RoleRepository;
import com.leozeballos.blog.repository.UserRepository;
import com.leozeballos.blog.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    
    @Override
    public String login(LoginDTO loginDTO) {
        // authenticate the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate the token
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        // check if username and email are taken
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        // create new user's account
        Set<Role> role = Collections.singleton(roleRepository.findRoleByName("USER").get());
        User user = User.builder()
                .name(registerDTO.getName())
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .roles(role)
                .build();
        userRepository.save(user);

        // authenticate the user and return the token
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                registerDTO.getUsername(),
                registerDTO.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate the token
        return jwtTokenProvider.generateToken(authentication);
    }
    
}
