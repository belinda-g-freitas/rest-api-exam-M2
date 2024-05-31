package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "debutSuivi is required, must be a valid date and not not be in the future")
  private Date debutSuivi;

  @NotBlank(message = "finSuivi is required, must be a valid date and not not be in the past")
  private Date finSuivi;
  
  @NotBlank(message = "sellerId  is required and must be not null and not empty")
  private String sellerId;

  @NotBlank(message = "storeId is required and must be not null and not empty")
  private String storeId;
}
