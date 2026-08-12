//package com.mit.SecureFileStorage.auth.util;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.io.Decoders;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import javax.crypto.SecretKey;
//@Component
//public class JwtUtils {
//
//    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour
//
//    // Base64-encoded 256-bit secret (must be 32 bytes)
//    private final String SECRET = "4D635166546A576E5A7234753778214125442A472D4B6150645367566B597033"; // example
//    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                .signWith(key)
//                .compact();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            getParser().parseClaimsJws(token);
//            return true;
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//
//    public String getUsernameFromToken(String token) {
//        return getParser()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    private JwtParser getParser() {
//        return Jwts.parser().verifyWith(key).build();
//    }
//}
