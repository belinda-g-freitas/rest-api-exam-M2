package com.esgis.venteapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    private String id;
    private String name;
}
