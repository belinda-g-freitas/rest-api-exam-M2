package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.Superviseur;

public interface SuperviseurRepository extends MongoRepository<Superviseur, String> {
  public Superviseur findByUsername(String username);
}
