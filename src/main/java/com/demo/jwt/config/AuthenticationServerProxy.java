package com.demo.jwt.config;

import com.demo.jwt.model.authentication.UserAuthentication;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServerProxy {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";
        var body = new UserAuthentication();
        body.setUsername(username);
        body.setPassword(password);
        var request = new HttpEntity<>(body);
        restTemplate.postForEntity(url, request, Void.class);
    }

    public boolean sendOTP(String username, String code) {
        String url = baseUrl + "/otp/check";
        var body = new UserAuthentication();
        body.setUsername(username);
        body.setCode(code);
        var request = new HttpEntity<>(body);
        ResponseEntity response = restTemplate.postForEntity(url, request, Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
