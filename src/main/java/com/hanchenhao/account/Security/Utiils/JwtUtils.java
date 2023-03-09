package com.hanchenhao.account.Security.Utiils;

import io.jsonwebtoken.Jwts;

import java.util.Date;

public class JwtUtils {
    static public String createJwt(String s, int expiration) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration * 60 * 1000);
        return Jwts.builder().setSubject(s)
                .setExpiration(expirationDate)
                .compact();
    }
}
