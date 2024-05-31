package com.esgis.venteapi.models.DTOs;

import com.esgis.venteapi.models.AuthRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// @Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BoutiqueCreateDTO extends AuthRequestDTO {
  private String nomBoutique;
  private String telBoutique;
  private String addressBoutique;
}
