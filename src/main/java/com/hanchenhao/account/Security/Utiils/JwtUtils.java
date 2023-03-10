package com.hanchenhao.account.Security.Utiils;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtils {
    static final String jwtSecretKey = "mySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKey";
    static SecretKeySpec secretKey = new SecretKeySpec(jwtSecretKey.getBytes(StandardCharsets.UTF_8), "HMACSHA256");

    static public String createJwt(String s, int expiration) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration * 60 * 1000);
        return Jwts.builder().setSubject(s)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    static public String parseJwt(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();

    }

}
