package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgis.venteapi.models.Boutique;
import com.esgis.venteapi.repositories.BoutiqueRepository;

@Service
public class BoutiqueServiceImpl implements BoutiqueService {

  @Autowired
  private BoutiqueRepository repository;

  public Boutique create(Boutique data) {
    return repository.save(data);
  }

  public Boutique update(Boutique data) {
    return repository.save(data);
  }

  public List<Boutique> findAll() {
    final List<Boutique> list = repository.findAll();
    // list.sort((a, b) -> a.getUsername().compareToIgnoreCase(b.getUsername()));

    return list;
  }

  public Optional<Boutique> findById(String id) {
    return repository.findById(id);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }
}
