package com.esgis.venteapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgis.venteapi.models.Produit;
import com.esgis.venteapi.services.ProduitService;

@RestController
@RequestMapping("/api/v1/produits")
public class ProduitController {
    
    @Autowired
    private ProduitService service;

    @PostMapping("/new")
    public Produit create(@RequestBody Produit produit) {
        return service.create(produit);
    }

    @GetMapping
    public List<Produit> findAllProduits() {
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    public Produit findOneProduits(@PathVariable String id) {
        Optional<Produit> produit = service.findById(id);
        if (produit.isPresent()) {
            return produit.get();
        }
        return null;
    }

    @PutMapping("/update/{id}")
    public Produit updateProduit(@PathVariable String id, @RequestBody Produit produit) {
        produit.setId(id);
        return service.update(produit);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduit(@PathVariable String id) {
        service.delete(id);
    }


}
