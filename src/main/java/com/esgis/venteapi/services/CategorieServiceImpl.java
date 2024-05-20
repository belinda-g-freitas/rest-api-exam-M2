package com.esgis.venteapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgis.venteapi.models.Categorie;
import com.esgis.venteapi.repositories.CategorieRepository;

@Service
public class CategorieServiceImpl implements CategorieService{
    
    @Autowired
    private CategorieRepository categorieRepository;

    public Categorie create(Categorie data){
        return categorieRepository.save(data);
    }
    public Categorie update(Categorie data) {
        return categorieRepository.save(data);
    }

    public List<Categorie> findAll(){
        return categorieRepository.findAll();
    }
    public Optional<Categorie> findById(String id){
        return categorieRepository.findById(id);
    }
    public void delete(String id){

        categorieRepository.deleteById(id);
    }
}
