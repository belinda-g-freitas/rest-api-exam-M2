package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.Suivi;

public interface SuiviService {
  public Suivi create(Suivi data);

  public Suivi update(Suivi data);

  public List<Suivi> findAll();

  public Optional<Suivi> findById(String id);

  public void delete(String id);
}
