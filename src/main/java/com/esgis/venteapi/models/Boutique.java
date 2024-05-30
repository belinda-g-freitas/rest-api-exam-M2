package com.esgis.venteapi.models;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)

public class Boutique extends UserInfo {
   @NotBlank(message = "nomBoutique is required and must be not null and not empty")
   private String nomBoutique;

   @NotBlank(message = "telBoutique is required and must be not null and not empty")
   private String telBoutique;

   @NotBlank(message = "addressBoutique is required and must be not null and not empty")
   private String addressBoutique;
}
