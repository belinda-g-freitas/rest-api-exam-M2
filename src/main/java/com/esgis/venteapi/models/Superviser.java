package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "supervising")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Superviser {
  @Id
  private String id;

  @NotBlank(message = "debutSupervision is required, must be a valid date and not not be in the future")
  private Date debutSupervision;

  @NotBlank(message = "finSupervision is required, must be a valid date and not not be in the past")
  private Date finSupervision;
  // 
  @NotBlank(message = "sellerId is required and must not be empty")
  private String sellerId;

  @NotBlank(message = "supervisorId is required and must not be empty")
  private String supervisorId;
}
