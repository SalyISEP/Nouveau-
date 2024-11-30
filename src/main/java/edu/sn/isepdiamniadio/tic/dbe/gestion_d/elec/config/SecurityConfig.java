package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // Désactiver CSRF si nécessaire
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/transaction/save-transaction").permitAll() // Rendre l'URL accessible
//                        .anyRequest().authenticated() // Les autres URL nécessitent une authentification
//                )
//                .httpBasic(); // Ou configurez un autre mécanisme si nécessaire
//
//        return http.build();
//    }
}