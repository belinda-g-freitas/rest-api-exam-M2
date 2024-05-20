package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.Superviser;

public interface SuperviserService {
  public Superviser create(Superviser data);

  public Superviser update(Superviser data);

  public List<Superviser> findAll();

  public Optional<Superviser> findById(String id);

  public void delete(String id);
}
