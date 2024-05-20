package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
  private Date debutSuivi;
  private Date finSuivi;
  //
  private String agentId;
  // private String superviseurId;
}
