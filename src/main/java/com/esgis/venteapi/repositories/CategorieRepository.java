package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.Categorie;

public interface CategorieRepository extends MongoRepository<Categorie, String>{
    
}
