package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgis.venteapi.models.Suivi;
import com.esgis.venteapi.repositories.SuiviRepository;

@Service
public class SuiviServiceImpl implements SuiviService {

  @Autowired
  private SuiviRepository repository;

  public Suivi create(Suivi data) {
    return repository.save(data);
  }

  public Suivi update(Suivi data) {
    return repository.save(data);
  }

  public List<Suivi> findAll() {
    return repository.findAll();
  }

  public Optional<Suivi> findById(String id) {
    return repository.findById(id);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }
}
