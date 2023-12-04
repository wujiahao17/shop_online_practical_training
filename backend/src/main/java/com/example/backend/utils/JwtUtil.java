package com.example.backend.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    // 创建token
    public static String createToken(Long id,String secret) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return Jwts.builder()
                .setClaims(map) //设置响应数据体
                .signWith(SignatureAlgorithm.HS256, secret) //设置加密方法和加密盐
                .compact();
    }


    // 解析token
    public static Map parseToken(String token,String secret) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
