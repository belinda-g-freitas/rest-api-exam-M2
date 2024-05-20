package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.Superviseur;

public interface SuperviseurService {
  public Superviseur create(Superviseur data);

  public Superviseur update(Superviseur data);

  public List<Superviseur> findAll();

  public Optional<Superviseur> findById(String id);

  public void delete(String id);
}
