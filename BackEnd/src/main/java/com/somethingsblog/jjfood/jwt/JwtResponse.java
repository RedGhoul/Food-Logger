package com.somethingsblog.jjfood.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String jwt;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
