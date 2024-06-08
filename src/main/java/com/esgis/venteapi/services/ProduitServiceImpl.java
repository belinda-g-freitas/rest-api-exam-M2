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

	@Override
	public List<Produit> findByStore(String id) {
		return repository.findByStoreId(id);
	}

	@Override
	public List<Produit> findByCategory(String id) {
		return repository.findByCategoryId(id);
	}

	@Override
	public List<Produit> findByProductName(String name) {
		return repository.findByNomProduit(name);
	}

	@Override
	public Optional<Produit> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Object changeState(String id, boolean isActive) {
		final Optional<Produit> data = repository.findById(id);
		
		if (data.isPresent()) {
			final Produit result = data.get();
			result.setActive(isActive);
		
			return repository.save(result);
		}
		
		return null;
	}

	public void delete(String id) {
		repository.deleteById(id);
	}
}
