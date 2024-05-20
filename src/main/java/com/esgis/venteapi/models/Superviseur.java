package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "supervisors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)

public class Superviseur extends UserInfo {
  private String nomSuperviseur;
  private String prenomSuperviseur;
  // 
  private Date debutSupervis;
  private Date finSupervis;
}
