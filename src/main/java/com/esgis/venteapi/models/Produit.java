package com.esgis.venteapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
   @Id
   private String id;

   @NotBlank(message = "codeProduit is required and must be not null and not empty")
   private String codeProduit;
   
   @NotBlank(message = "nomProduit is required and must be not null and not empty")
   private String nomProduit;

   @NotBlank(message = "categorieId is required and must be not null and not empty")
   private String categorieId;

   @NotBlank(message = "nomBoutique is required and must be not null and not empty")
   private String storeId;
}
