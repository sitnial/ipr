package com.iba.ipr.security;

import com.iba.ipr.entity.RoleName;
import com.iba.ipr.entity.User;
import io.jsonwebtoken.Jws;
import lombok.extern.log4j.Log4j;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static com.iba.ipr.util.JwtConstants.*;

@Component
@Log4j
public class JwtProvider {
    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(@NotNull User user) {
        Date date = Date.from(LocalDateTime.now().plusHours(1)
                .atZone(ZoneId.systemDefault()).toInstant());
        Set<RoleName> authorityRoles = user.getUserRoles().stream()
                .map(x -> x.getRole().getRoleName())
                .collect(Collectors.toSet());
        return Jwts.builder()
                .setExpiration(date)
                .claim(TOKEN_CLAIM_USER_ID, user.getId())
                .claim(TOKEN_CLAIM_USERNAME, user.getName())
                .claim(TOKEN_CLAIM_EMAIL, user.getEmail())
                .claim(TOKEN_CLAIM_ROLES, authorityRoles)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public Claims getClaims(String token) {
        return getJwsClaims(token, jwtSecret).getBody();
    }

    private Jws<Claims> getJwsClaims(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}