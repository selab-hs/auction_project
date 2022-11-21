package com.selab.auction.member.signin.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {
    @Value("app.jwt.secret")
    private String secret;

    private final long TOKEN_EXPIRY_DUR = 864000000;

    public String createToken(String loginMember) {
        Claims claims = Jwts.claims().setSubject(loginMember);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TOKEN_EXPIRY_DUR);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getMemberIdFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            log.info("token Expired");
            return e.getClaims().getSubject();
        }
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return false;
    }
}
