package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.config;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.AuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);  // Déclarez le logger

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Utilisation de BCryptPasswordEncoder
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.debug("Configuring security filter chain");  // Log de débogage
       return http
                .csrf(csrf -> csrf.disable())  // Désactivation de la protection CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/api/auth/login", "/api/auth/logout").permitAll()
                        // .requestMatchers("/admin/**").hasRole("ADMIN")
                        // .requestMatchers("/client/**").hasRole("CLIENT")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                //.authenticationProvider(authenticationProvider)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build(); // Pas de session

    }
}


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
//    }@Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                //Le package builder nous permet de faire cette insturction(c'est-adrie le fait de mettre chaque debut un point de au final on aura une seule ;) ci-dessus:
//                .csrf(csrf->csrf.disable())//autorise les requetes cors(crosse origine request site
//                .authorizeRequests(authorize->
//
//                        authorize.anyRequest().permitAll())//autorise l'acces a toutes les url
//                        //authorize.requestMatchers("/marques/search").authenticated().anyRequest().permitAll())//autorise l'acces a toutes les url
//                .sessionManagement(sessionManagement->{
//                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);//permet de dire que chaque requete est independant(y'a pas de session
//                })
//        ;
//        return  http.build();
//    }
