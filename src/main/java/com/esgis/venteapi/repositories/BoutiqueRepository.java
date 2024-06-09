package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.Boutique;

public interface BoutiqueRepository extends MongoRepository<Boutique, String> {
  public Boutique findByUsername(String username);
}
