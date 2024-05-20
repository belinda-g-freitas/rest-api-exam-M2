package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import com.esgis.venteapi.models.Categorie;

public interface CategorieService {
    public Categorie create(Categorie data);

    public Categorie update(Categorie data);

    public List<Categorie> findAll();

    public Optional<Categorie> findById(String id);

    public void delete(String id);
}
