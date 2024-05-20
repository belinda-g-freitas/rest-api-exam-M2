package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgis.venteapi.models.Approvisionnement;
import com.esgis.venteapi.repositories.ApprovisionnementRepository;

@Service
public class ApprovisionnementServiceImpl implements ApprovisionnementService {

  @Autowired
  private ApprovisionnementRepository repository;

  public Approvisionnement create(Approvisionnement data) {
    return repository.save(data);
  }

  public Approvisionnement update(Approvisionnement data) {
    return repository.save(data);
  }

  public List<Approvisionnement> findAll() {
    return repository.findAll();
  }

  public Optional<Approvisionnement> findById(String id) {
    return repository.findById(id);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }

  @Override
  public Optional<Approvisionnement> findById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void delete(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
}
