package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.Approvisionnement;

public interface ApprovisionnementService {
  public Approvisionnement create(Approvisionnement data);

  public Approvisionnement update(Approvisionnement data);

  public List<Approvisionnement> findAll();

  public Optional<Approvisionnement> findById(int id);

  public void delete(int id);
}
