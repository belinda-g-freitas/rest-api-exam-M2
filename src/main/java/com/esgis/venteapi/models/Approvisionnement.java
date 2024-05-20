package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "supplies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Approvisionnement {
   @Id
   private int id;
   private int quantiteStock;
   private Date dateStock;
}
