package com.esgis.venteapi.models;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "sellers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)

public class AgentVendeur extends UserInfo {
  @NotBlank(message = "nomAgent is required and must be not null and not empty")
  private String nomAgent;

  @NotBlank(message = "prenomAgent is required and must be not null and not empty")
  private String prenomAgent;
}
