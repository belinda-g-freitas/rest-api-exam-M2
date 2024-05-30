package com.esgis.venteapi.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
	@NotBlank(message = "username is required and must be not null and not empty")
	private String username;

	@NotBlank(message = "password is required and must be not null and not empty")
	private String password;
}

