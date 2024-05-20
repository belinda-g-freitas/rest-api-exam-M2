package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.Vente;

public interface VenteService {
  public Vente create(Vente data);

  public Vente update(Vente data);

  public List<Vente> findAll();

  public Optional<Vente> findById(String id);

  public void delete(String id);
}
