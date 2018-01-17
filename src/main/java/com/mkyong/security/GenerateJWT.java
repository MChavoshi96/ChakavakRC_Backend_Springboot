package com.mkyong.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GenerateJWT {

    public static void main(String[] args) {
        String token = Jwts.builder()
                .setId("user")
                .claim("username", "hello")
                .setSubject("ali")
                .signWith(SignatureAlgorithm.HS256, "rahnema").compact();

        System.out.printf(token);

    }
}
