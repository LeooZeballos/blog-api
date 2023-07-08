package com.leozeballos.blog.service;

import com.leozeballos.blog.dto.LoginDTO;
import com.leozeballos.blog.dto.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
}
