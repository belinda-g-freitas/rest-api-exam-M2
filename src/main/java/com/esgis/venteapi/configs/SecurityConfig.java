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
						.requestMatchers("/api/v1/users/login")
						.permitAll()
						.requestMatchers("/api/v1/**").authenticated()
						//
						.requestMatchers("/api/v1/users/signup", "/api/v1/users/update").hasRole("SUPERADMIN")
						//
						.requestMatchers("/api/v1/stores/new", "/api/v1/stores/delete", "/api/v1/sellers/delete",
								"/api/v1/supervisors/delete",
								"/api/v1/tracking/delete", "/api/v1/supervising/delete", "/api/v1/selling/delete",
								"/api/v1/products/delete", "/api/v1/categories/delete", "/api/v1/supplies/delete")
						.hasAnyRole("SUPERADMIN", "OWNER")
						//
						.requestMatchers("/api/v1/stores/find", "/api/v1/sellers/find", "/api/v1/sellers/update",
								"/api/v1/tracking/new", "/api/v1/tracking/find", "/api/v1/selling/new", "/api/v1/products/new",
								"/api/v1/products/find", "/api/v1/products/update", "/api/v1/categories/new", "/api/v1/categories/find",
								"/api/v1/supplies/new", "/api/v1/supplies/update", "/api/v1/supplies/find")
						.hasAnyRole("USER", "ADMIN", "OWNER", "SUPERADMIN")
						//
						.requestMatchers("/api/v1/stores/**", "/api/v1/sellers/**", "/api/v1/supervisors/**",
								"/api/v1/tracking/**", "/api/v1/supervising/**", "/api/v1/selling/**", "/api/v1/products/**",
								"/api/v1/categories/**", "api/v1/supplies/**")
						.hasAnyRole("ADMIN", "SUPERADMIN", "OWNER"))
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
