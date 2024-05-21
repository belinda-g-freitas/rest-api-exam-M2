package com.esgis.venteapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.esgis.venteapi.models.AuthRequestDTO;
import com.esgis.venteapi.models.JwtResponseDTO;
import com.esgis.venteapi.models.UserInfo;
import com.esgis.venteapi.repositories.UserRepository;
import com.esgis.venteapi.services.JwtService;

@RequestMapping("/api/v1/users")
@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/api/v1/adminProfile")
	public String adminProfile() {
		try {
			return "Welcome ADMIN";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/api/v1/userProfile")
	public String userProfile() {
		try {
			return "Welcome User";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping("/login")
	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
		if (authentication.isAuthenticated()) {
			return JwtResponseDTO.builder()
					.accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
		} else {
			throw new UsernameNotFoundException("invalid user request..!!");
		}
	}

	@PostMapping("/signup")
	public UserInfo addNewUser(@RequestBody UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		return repository.save(userInfo);
	}

}
