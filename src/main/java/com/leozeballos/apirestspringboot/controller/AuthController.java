package com.leozeballos.apirestspringboot.controller;

import com.leozeballos.apirestspringboot.dto.LoginDTO;
import com.leozeballos.apirestspringboot.dto.RegisterDTO;
import com.leozeballos.apirestspringboot.entity.Role;
import com.leozeballos.apirestspringboot.entity.User;
import com.leozeballos.apirestspringboot.repository.RoleRepository;
import com.leozeballos.apirestspringboot.repository.UserRepository;
import com.leozeballos.apirestspringboot.security.JWTAuthResponseDTO;
import com.leozeballos.apirestspringboot.security.JwtTokenProvider;
import com.leozeballos.apirestspringboot.utility.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/api/" + AppConstants.API_VERSION + "/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponseDTO(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        } else if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return new ResponseEntity<>("Email is already in use!", HttpStatus.BAD_REQUEST);
        } else {
            Set<Role> role = Collections.singleton(roleRepository.findRoleByName("ROLE_ADMIN").get());
            User user = User.builder()
                    .name(registerDTO.getName())
                    .username(registerDTO.getUsername())
                    .email(registerDTO.getEmail())
                    .password(passwordEncoder.encode(registerDTO.getPassword()))
                    .roles(role)
                    .build();
            userRepository.save(user);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
        }
    }

}
