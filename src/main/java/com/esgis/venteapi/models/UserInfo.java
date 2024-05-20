package com.esgis.venteapi.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    private int id;
    private String username;
    private String password;
    private Set<UserRole> roles = new HashSet<>();

    public String toString() {
        return username;
    }

}
