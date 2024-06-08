package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.Produit;

public interface ProduitService {
    public Produit create(Produit data);
    public Produit update(Produit data);
    public List<Produit> findAll();
    public List<Produit> findByCategory(String id);
    public List<Produit> findByStore(String id);
    public List<Produit> findByProductName(String name);
    public Optional<Produit> findById(String id);
    // public Object changeState(String id, boolean isActive);
    public void delete(String id);
}
