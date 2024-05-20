package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.AgentVendeur;

public interface AgentVendeurService {
  public AgentVendeur create(AgentVendeur data);

  public AgentVendeur update(AgentVendeur data);

  public List<AgentVendeur> findAll();

  public Optional<AgentVendeur> findById(String id);

  public void delete(String id);
}
