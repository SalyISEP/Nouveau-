package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final AuthService authService;

    @Autowired
    public JwtUtil( AuthService authService) {
        this.authService = authService;
    }
    public String generateToken(String email) {
        // Logique pour générer un token JWT
        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS256, "SECRET_KEY")
                .compact();
    }
}
