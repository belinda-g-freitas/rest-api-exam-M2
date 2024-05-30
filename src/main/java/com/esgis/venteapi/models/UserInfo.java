package com.esgis.venteapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    private String id;

    @NotBlank(message = "username is required and must be not null and not empty")
    private String username;

    @NotBlank(message = "password is required and must be not null and not empty")
    private String password;
    // private Role role;
    private String role;

    public String toString() {
        return username;
    }

}
