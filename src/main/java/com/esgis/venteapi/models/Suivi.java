package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tracking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suivi {
  @Id
  private String id;

  @NotNull(message = "debutSuivi is required, must be a valid date and not be in the future")
  private Date debutSuivi;

  @NotNull(message = "finSuivi is required, must be a valid date and not be in the past")
  private Date finSuivi;
  
  @NotBlank(message = "sellerId  is required and must be not null and not empty")
  private String sellerId;

  @NotBlank(message = "storeId is required and must be not null and not empty")
  private String storeId;
}
