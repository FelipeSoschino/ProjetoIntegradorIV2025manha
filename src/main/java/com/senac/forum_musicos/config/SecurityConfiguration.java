package com.senac.forum_musicos.config;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SecurityConfiguration {

        @Autowired
        private UserAuthenticationFilter userAuthenticationFilter;

        public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
                        "/api/usuario/login", // Url que usaremos para fazer login
                        "/api/usuario/criar",
                        "/api/admin/reset", // Url que usaremos para criar um usuário
                        // 🔓 Swagger/OpenAPI UI
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
        };
        // Endpoints que requerem autenticação para serem acessados
        public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
                        "/api/usuario/listar",
                        "/api/topico/listar",

        };
        // Endpoints que só podem ser acessador por usuários com permissão de cliente
        public static final String[] ENDPOINTS_CUSTOMER = {
                        "/api/topico/**",
                        "/api/post/**",
                        "/api/participa/**",
                        "/api/curtida/**",
                        "/api/instrumento/**",
                        "/api/comentario/**"
        };
        // Endpoints que só podem ser acessador por usuários com permissão de
        // administrador
        public static final String[] ENDPOINTS_ADMIN = {
                        "/instrumento"
        };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .cors(org.springframework.security.config.Customizer.withDefaults()) // Enable CORS
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // adicionado
                                                                                                        // para
                                                                                                        // funcionamento
                                                                                                        // do swagger
                                                .requestMatchers(ENDPOINTS_ADMIN).permitAll() // Disabling role check
                                                .requestMatchers(ENDPOINTS_CUSTOMER).permitAll() // Disabling role check
                                                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).permitAll() // Disabling
                                                                                                                     // auth
                                                                                                                     // check
                                                .anyRequest().permitAll() // Allow everything
                                )
                                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
                org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
                configuration.setAllowedOrigins(java.util.Arrays.asList("*"));
                configuration.setAllowedMethods(java.util.Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS",
                                "HEAD", "TRACE", "CONNECT"));
                configuration.setAllowedHeaders(java.util.Arrays.asList("*"));
                org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}