package com.rahul.springjwt.security.service;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {
    private String jwtSecret = "veryConfidentialSecret";

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpiration;

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    public String generateJWToken(Authentication authentication){
        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + jwtExpiration);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String builder = Jwts.builder().setSubject((userDetails.getUsername()))
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return builder;

    }

    public String getUserNameFromJWTToken(String jwtToken){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
                String userName = claimsJws.getBody().getSubject();
                return userName;
    }

    public boolean validateJWTToken(String jwtToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
