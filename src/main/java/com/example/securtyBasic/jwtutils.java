package com.example.securtyBasic;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class jwtutils {
    // 256-bit Base64 encoded secret [06:23:13]
    private String jwtSecret = "Zm9vYmFyYmF6cXV4cXV1eGNvcnRlZ2U0M3JlZ2VyZ2V3cmVncmVncmVncmU=";
    private int jwtExpirationMs = 172800000;

    public String generateTokensFromUsername(String userName) {
        return Jwts.builder()
                .subject(userName) // 0.12.x syntax
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key()) // Algorithm detected automatically
                .compact();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}