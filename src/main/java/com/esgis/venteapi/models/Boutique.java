package com.esgis.venteapi.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)

public class Boutique extends UserInfo {
   private String nomBoutique;
   private String telBoutique;
   private String addressBoutique;
}
