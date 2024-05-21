package com.esgis.venteapi.models.DTOs;

import com.esgis.venteapi.models.AuthRequestDTO;
import com.esgis.venteapi.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// @Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class VendeurUpdateDTO extends AuthRequestDTO {
  private String username;
  private String password;
  private Role role;
  private String nomAgent;
  private String prenomAgent;
}
