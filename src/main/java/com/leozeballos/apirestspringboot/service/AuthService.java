package com.leozeballos.apirestspringboot.service;

import com.leozeballos.apirestspringboot.dto.LoginDTO;
import com.leozeballos.apirestspringboot.dto.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
}
