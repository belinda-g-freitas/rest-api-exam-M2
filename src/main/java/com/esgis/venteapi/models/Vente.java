package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
   private Date dateVente;
   private int qteVendue;
}
