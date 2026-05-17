package com.example.raizes_do_nordeste.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita proteção contra ataques de sessão (pois usaremos JWT)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // A API não guarda sessão (estado)
                .authorizeHttpRequests(req -> {
                    
                    // Rotas publicas
                    req.requestMatchers(HttpMethod.POST, "/usuarios").permitAll(); 
                    req.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
                    
                    //Rotas ADMIN
                    req.requestMatchers(HttpMethod.POST, "/produtos", "/unidades", "/estoque").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.PUT, "/produtos/**", "/unidades/**", "/estoque/**").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.DELETE, "/produtos/**", "/unidades/**", "/estoque/**").hasRole("ADMIN");

                    //Rotas compartilhadas para usuarios logados
                    req.requestMatchers(HttpMethod.POST, "/pedidos", "/pagamentos**").authenticated();
                    req.requestMatchers(HttpMethod.GET, "/produtos/**", "/unidades/**", "/estoque/**", "/pedidos/**").authenticated();

                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
