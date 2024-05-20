package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgis.venteapi.models.Produit;
import com.esgis.venteapi.repositories.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService {

	@Autowired
	private ProduitRepository repository;

	public Produit create(Produit data) {
		return repository.save(data);
	}

	public Produit update(Produit data) {
		return repository.save(data);
	}

	public List<Produit> findAll() {
		return repository.findAll();
	}

	public Optional<Produit> findById(String id) {
		return repository.findById(id);
	}

	public void delete(String id) {
		repository.deleteById(id);
	}
}
