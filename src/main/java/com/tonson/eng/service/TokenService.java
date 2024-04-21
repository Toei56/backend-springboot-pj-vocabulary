package com.tonson.eng.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tonson.eng.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Service
public class TokenService {

    @Value("${spring.token.secret}") //วิธีดึงค่าจาก .yml มาใช้
    private String secret;
    @Value("${spring.token.issuer}")
    private String issuer;

    public String Tokenize(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        Instant expiresAt = Instant.now().plus(Duration.ofDays(30)); // token มีอายุ 30 วัน

        return JWT
                .create()
                .withIssuer(issuer) //ผู้สร้าง token
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }
}
