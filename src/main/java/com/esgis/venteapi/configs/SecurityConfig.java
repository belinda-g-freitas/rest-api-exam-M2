package com.esgis.venteapi.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.esgis.venteapi.filters.JwtAuthFilter;
import com.esgis.venteapi.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disabling CSRF protection for stateless session policy
                .csrf(CsrfConfigurer::disable)
                // Authorize requests
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/users/signUp", "/api/v1/users/login").permitAll()
                        .requestMatchers("/api/v1/**").authenticated()
                        .requestMatchers("/api/v1/stores").hasAnyRole("SELLER", "STORE")
                        .requestMatchers("/api/v1/sellers").hasAnyRole("SUPERVISOR", "STORE", "SELLER")
                        .requestMatchers("/api/v1/supervisor").hasAnyRole("SUPERVISOR", "STORE")
                        .requestMatchers("/api/v1/tracking").hasAnyRole("SELLER", "STORE")
                        .requestMatchers("/api/v1/supervising").hasAnyRole("SUPERVISOR")
                        .requestMatchers("/api/v1/selling").hasAnyRole("SELLER", "STORE")
                        )
                // Session management
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Authentication provider
                .authenticationProvider(authenticationProvider())
                // Add filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // return http.csrf().disable()
    // .authorizeHttpRequests()
    // .requestMatchers("/api/v1/login").permitAll()
    // .and()
    // .authorizeHttpRequests().requestMatchers("/api/v1/**")
    // .authenticated()
    // .and()
    // .sessionManagement()
    // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // .and()
    // .authenticationProvider(authenticationProvider())
    // .addFilterBefore(jwtAuthFilter,
    // UsernamePasswordAuthenticationFilter.class).build();

    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
