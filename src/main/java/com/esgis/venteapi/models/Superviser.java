package com.esgis.venteapi.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "supervising")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Superviser {
  @Id
  private String id;
  private Date debutSupervision;
  private Date finSupervision;
  // 
  private String agentId;
  private String superviseurId;
}
