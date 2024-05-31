package com.esgis.venteapi.models;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "supervisors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)

public class Superviseur extends UserInfo {
  @NotBlank(message = "nomSuperviseur is required and must be not null and not empty")
  private String nomSuperviseur;

  @NotBlank(message = "prenomSuperviseur is required and must be not null and not empty")
  private String prenomSuperviseur;
}
