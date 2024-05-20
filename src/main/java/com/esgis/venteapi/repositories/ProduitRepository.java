package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.Produit;

public interface ProduitRepository extends MongoRepository<Produit, String>{
    
}
