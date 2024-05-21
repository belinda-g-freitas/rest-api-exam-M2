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
						.requestMatchers("/api/v1/users/signup", "/api/v1/users/login",
								"/api/v1/supervisors/signup", "/api/v1/sellers/signup", "/api/v1/stores/signup")
						.permitAll()
						.requestMatchers("/api/v1/**").authenticated()
						// stores
						.requestMatchers("/api/v1/stores/find").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/api/v1/stores/**").hasRole("ADMIN")
						// sellers
						.requestMatchers("/api/v1/sellers/find").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/api/v1/sellers/**").hasRole("ADMIN")
						// supervisor
						.requestMatchers("/api/v1/supervisors/find").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/api/v1/supervisors/**").hasRole("ADMIN")
						// tracking
						.requestMatchers("/api/v1/tracking/find").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/api/v1/tracking/**").hasRole("ADMIN")
						// supervising
						.requestMatchers("/api/v1/supervising/find").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/api/v1/supervising/**").hasRole("ADMIN")
						// selling
						.requestMatchers("/api/v1/selling/find").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/api/v1/selling/**").hasRole("ADMIN")
						// selling
						.requestMatchers("/api/v1/categories/find").hasAnyRole("ADMIN", "USER")
						.requestMatchers("/api/v1/categories/**").hasAnyAuthority("ADMIN", "SUPERADMIN"))
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
