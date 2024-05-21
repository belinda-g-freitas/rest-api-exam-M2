package com.esgis.venteapi.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
   @Id
   private String id;
   
   @NotBlank(message = "nomCategorie is required and must be not null")
   private String nomCategorie;
}
