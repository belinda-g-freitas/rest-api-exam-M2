package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgis.venteapi.models.Vente;
import com.esgis.venteapi.repositories.VenteRepository;

@Service
public class VenteServiceImpl implements VenteService {

  @Autowired
  private VenteRepository venteRepository;

  public Vente create(Vente data) {
    return venteRepository.save(data);
  }

  public Vente update(Vente data) {
    return venteRepository.save(data);
  }

  public List<Vente> findAll() {
    return venteRepository.findAll();
  }

  public Optional<Vente> findById(String id) {
    return venteRepository.findById(id);
  }

  public void delete(String id) {
    venteRepository.deleteById(id);
  }
}
