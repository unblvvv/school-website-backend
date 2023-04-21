package ua.unblv.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.unblv.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenProvider {
    public static final Logger LOGGER = LoggerFactory.getLogger(JWTTokenProvider.class);

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date dateNow = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(dateNow.getTime() + SecurityConstants.EXPIRATION_TIME);

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", Long.toString(user.getId()));
        claimsMap.put("username", user.getUsername());
        claimsMap.put("firstname", user.getName());
        claimsMap.put("lastname", user.getLastname());

        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .addClaims(claimsMap)
                .setIssuedAt(dateNow)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.ES512, SecurityConstants.SECRET)
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }

}
