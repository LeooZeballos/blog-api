package com.leozeballos.blog.controller;

import com.leozeballos.blog.dto.LoginDTO;
import com.leozeballos.blog.dto.RegisterDTO;
import com.leozeballos.blog.security.JWTAuthResponseDTO;
import com.leozeballos.blog.service.AuthService;
import com.leozeballos.blog.utility.AppConstants;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/" + AppConstants.API_VERSION + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<JWTAuthResponseDTO> registerUser(@RequestBody RegisterDTO registerDTO) {
        try {
            String token = authService.register(registerDTO);
            return ResponseEntity.ok(new JWTAuthResponseDTO(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
