package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;



    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Value("${jwt.secret}")
    private String secretKey  ;// Clé secrète pour signer le token

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Récupérer l'en-tête Authorization
        String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Vérifier si l'en-tête contient le token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Enlève "Bearer "
            try {
                // Extraire le nom d'utilisateur du token
                username = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(jwt)
                        .getBody()
                        .getSubject();

                if (isTokenExpired(jwt)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expiré");
                    return; // Ne pas continuer la chaîne si le token est expiré
                }
            } catch (ExpiredJwtException e) {
                logger.warn("Token expiré");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expiré");
                return; // Ne pas continuer la chaîne
            } catch (Exception e) {
                logger.warn("Token invalide");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalide");
                return; // Ne pas continuer la chaîne
            }
        }

        // Si l'utilisateur est trouvé dans le token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continuer le filtre de la chaîne
        filterChain.doFilter(request, response);
    }

    private boolean isTokenExpired(String token) {
        try {
            Date expirationDate = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expirationDate.before(new Date());
        } catch (Exception e) {
            return true; // Considérer le token comme expiré en cas d'erreur
        }
    }
}