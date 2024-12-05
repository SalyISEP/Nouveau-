package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private  String SECRET;

    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    public JwtUtil() {

    }
    public String generateToken(String email) {
        // Logique pour générer un token JWT
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
