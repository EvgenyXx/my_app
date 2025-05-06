package com.example.my_app.config.jwt;

import com.example.my_app.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.*;


@Service
@RequiredArgsConstructor
public class JWTService {

    private static final String SECRET_KEY = "bNDoES6IHyt9YhNeF7GpFj1hRAhO0jDhXf3Dnk4fZl4=";


    private static final Date EXPIRATION_50_MINUTE = new Date(System.currentTimeMillis() + 1000 * 60 * 50);

    private static final Date EXPIRATION_ONE_WEEK = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7); // 7 дней

    private static final Date EXPIRATION_20_MINUTE = new Date(System.currentTimeMillis() + 1000 * 60 * 20);

    private static final String ROLES = "roles";


    /*
    Метод для создания токена
     */
    private String createAccessToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(EXPIRATION_50_MINUTE)
                .signWith(getSecretKey())
                .compact();
    }

    //генерация токена при создании
    public String generateAccessToken(String numberPhone) {
        Map<String, Object> claims = new HashMap<>();
        return createAccessToken(claims, numberPhone);
    }

    private String createRefreshToken(Map<String, Object> claims, String numberPhone) {
        return Jwts.builder()
                .claims(claims)
                .subject(numberPhone)
                .issuedAt(new Date())
                .expiration(EXPIRATION_ONE_WEEK)
                .signWith(getSecretKey())
                .compact();
    }

    private String createChangePasswordToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(EXPIRATION_20_MINUTE)
                .compact();
    }

    public String generateChangePasswordToken(String email, List<Role> roles) {
        Map<String, Object> claims = new HashMap<>();
        List<String> role = roles.stream()
                .map(Role::getRoleName)
                .toList();
        claims.put(ROLES, role);
        return createChangePasswordToken(claims, email);
    }

    public String generateRefreshToken(String numberPhone, List<Role> roles) {
        Map<String, Object> claims = new HashMap<>();
        List<String> role = roles.stream()
                .map(Role::getRoleName)
                .toList();
        claims.put(ROLES, role);
        return createRefreshToken(claims, numberPhone);
    }


    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String getEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        // Получаем роли как List<?> (неопределенный тип)
        List<?> roles = claims.get(ROLES, List.class);

        // Проверяем и приводим к List<String>
        if (roles != null) {
            return roles.stream()
                    .map(String::valueOf)  // Преобразуем каждый элемент в строку
                    .toList();  // Используем Stream.toList() для создания неизменяемого списка
        }
        return List.of();  //
    }

}
