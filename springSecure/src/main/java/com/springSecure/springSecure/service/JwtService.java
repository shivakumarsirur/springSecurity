package com.springSecure.springSecure.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY="hjsghjskyutdssjhsjssbjs";
    private static final Long EXPIRATION_TIME=86400000L;

    public  String genertaeToken(String username,String role){
     return    JWT.create()
                .withSubject(username)
                .withClaim("role",role)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
    public String validateToken(String token){
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build().verify(token).getSubject();
    }
}
