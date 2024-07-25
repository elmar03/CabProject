package com.example.cabproject.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AfterSignInResponseDto {
    private String token;

    private String emailAddress;

    private String password;

    private String role;

    @Override
    public String toString() {
        return "token = " + token + '\n' +
                "email = " + emailAddress + '\n' +
                "password = " + password + '\n' +
                "role = " + role;

    }
}
