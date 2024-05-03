package com.tonson.eng.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tonson.eng.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${spring.token.secret}") //วิธีดึงค่าจาก .yml มาใช้ *ควรเปลี่ยนทุก 3 เดือน
    private String secret;
    @Value("${spring.token.issuer}")
    private String issuer;

    public String Tokenize(User user) {
        Instant expiresAt = Instant.now().plus(Duration.ofMinutes(50)); // token มีอายุ 5 นาที

        return JWT
                .create()
                .withIssuer(issuer) //ผู้สร้าง token
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT
                    .require(algorithm())
                    .withIssuer(issuer)
                    .build();
            return verifier.verify(token);

        } catch (Exception ex) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }
}
