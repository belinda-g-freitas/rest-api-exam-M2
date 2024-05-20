package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.Boutique;

public interface BoutiqueService {
  public Boutique create(Boutique data);

  public Boutique update(Boutique data);

  public List<Boutique> findAll();

  public Optional<Boutique> findById(int id);

  public void delete(int id);
}
