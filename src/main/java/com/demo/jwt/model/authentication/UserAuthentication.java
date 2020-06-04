package com.demo.jwt.model.authentication;

import lombok.Data;

@Data
public class UserAuthentication {
    private String username;
    private String password;
    private String code;
}
