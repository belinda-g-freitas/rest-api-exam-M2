package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.AgentVendeur;

public interface AgentVendeurRepository extends MongoRepository<AgentVendeur, String> {
  public AgentVendeur findByUsername(String username);
}
