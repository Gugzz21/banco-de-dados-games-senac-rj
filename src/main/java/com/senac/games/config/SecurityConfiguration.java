package com.senac.games.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public class SecurityConfiguration {

        @Autowired
        private UserAuthenticationFilter userAuthenticationFilter;

        public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
                "/usuario/login", // Url que usaremos para fazer login
                "/usuario/criar", // Url que usaremos para criar um usuário

                //Swagger/OpenAPI UI,
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html"

        };

        // Endpoints que requerem autenticação para serem acessados
        public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
                "/usuario/listar"
        };

        // Endpoints que só podem ser acessador por usuários com permissão de cliente
        public static final String [] ENDPOINTS_CUSTOMER = {
                "/jogo"
        };

        // Endpoints que só podem ser acessador por usuários com permissão de administrador
        public static final String [] ENDPOINTS_ADMIN = {
                "/usuario/adm"
        };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                            .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR")
                            .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
                            .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                            .anyRequest().denyAll()
                    )
                    .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }


