package com.balaji.springappsecurityjwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    String Secret_Key= "SecretKey";
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return  createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *10))
                .signWith(SignatureAlgorithm.HS512, Secret_Key).compact();
    }


    public String extractUserName(String jwt) {
        return extractClaims(jwt, Claims::getSubject);
    }

    private <T>T extractClaims(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims= extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().setSigningKey(Secret_Key).parseClaimsJws(jwt).getBody();
    }

    public boolean validateToken(String jwt, UserDetails userDetails) {
        return true;
    }
}
