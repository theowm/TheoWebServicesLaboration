package me.theowm.theowebserviceslaboration.configs;

import me.theowm.theowebserviceslaboration.converters.JwtAuthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Autowired
    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers("/api/v2/posts").authenticated()
                                .requestMatchers("/api/v2/post/{postId}").authenticated()
                                .requestMatchers("/api/v2/newpost").hasAnyRole("myclient_user", "myclient_admin")
                                .requestMatchers("/api/v2/updatepost").hasAnyRole("myclient_user", "myclient_admin")
                                .requestMatchers("/api/v2/deletepost/{id}").hasAnyRole("myclient_user", "myclient_admin")
                                .requestMatchers("/api/v2/count").hasRole("myclient_admin")
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2->
                        oauth2
                                .jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );
                return http.build();

    }
}
