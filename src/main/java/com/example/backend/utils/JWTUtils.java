package com.example.backend.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    // 创建token
    public static String generateToken(String secret, Map<String, Object> claims) {
        String result = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return result;
    }


    // 解析token
    public static Map parseToken(String secret,String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
