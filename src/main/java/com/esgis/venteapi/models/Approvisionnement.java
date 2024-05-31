package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "supplies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Approvisionnement {
   @Id
   private String id;
   @NotBlank(message = "quantiteStock is required and must be >= 1")
   private int quantiteStock;

   @NotBlank(message = "dateStock is required, must be a valid date and not not be in the future")
   private Date dateStock;
   //
   @NotBlank(message = "produitId is required and must be not null and not empty")
   private String produitId;

   @NotBlank(message = "boutiqueId is required and must be not null and not empty")
   private String storeId;

   @NotBlank(message = "vendeurId is required and must be not null and not empty")
   private String sellerId;
}
