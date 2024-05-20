package com.esgis.venteapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
   private String codeProduit;
   private String nomProduit;
   // private int quantite;
   // private double prixAchat;
   // private double prixVente;
   // // private Boolean perissable;
   private Categorie categorie;
}
