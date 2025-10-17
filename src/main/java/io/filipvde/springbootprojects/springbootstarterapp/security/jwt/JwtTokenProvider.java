package org.fve.springbootprojects.springbootstarterapp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@EnableConfigurationProperties(value = JwtProperties.class)
public class JwtTokenProvider {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Generate JWT token
     *
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtProperties.getExpirationMilliseconds());

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtProperties.getSecretKey())
        );
    }

    /**
     * Get username from JWT token
     *
     * @param token
     * @return
     */
    public String getUsername(String token) {
//        Claims claims = Jwts.parserBuilder()
        Claims claims = Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();

        return username;
    }

    /**
     * Validate JWT Token
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
//        Jwts.parserBuilder()
        Jwts.parser()
                .setSigningKey(key())
                .build()
                .parse(token);
        return true;
    }
}
