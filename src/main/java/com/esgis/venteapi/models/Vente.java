package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "selling")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vente {
   @Id
   private String id;
   
   @NotBlank(message = "dateVente is required and must be not null, valid and not in the future")
   private Date dateVente;

   @NotBlank(message = "qteVendue is required and must be not null and not empty")
   private int qteVendue;
   // 
   @NotBlank(message = "produitId is required and must be not null and not empty")
   private String produitId;
}
