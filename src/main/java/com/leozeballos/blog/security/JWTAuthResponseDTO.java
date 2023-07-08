package com.leozeballos.blog.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthResponseDTO {

    private String token;
    private String tokenType = "Bearer";

    public JWTAuthResponseDTO(String token) {
        this.token = token;
    }

}
