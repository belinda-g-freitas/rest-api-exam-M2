package com.esgis.venteapi.models.DTOs;

import com.esgis.venteapi.models.AuthRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorCreateDTO extends AuthRequestDTO {
  private String nomSuperviseur;
  private String prenomSuperviseur;
}
