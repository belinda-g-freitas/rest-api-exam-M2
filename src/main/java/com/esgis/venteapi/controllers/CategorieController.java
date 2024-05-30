package com.esgis.venteapi.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgis.venteapi.models.Categorie;
import com.esgis.venteapi.services.CategorieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {

    @Autowired
    private CategorieService service;

    @PostMapping("/new")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Categorie categorie) {
        final Categorie data = service.create(categorie);
        return new ResponseEntity<>(Map.of("message", "Success", "category", data), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Categorie> findAllCategories() {
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    public Categorie findOneCategories(@PathVariable String id) {
        Optional<Categorie> categorie = service.findById(id);
        if (categorie.isPresent()) {
            return categorie.get();
        }
        return null;
    }

    @PutMapping("/update/{id}")
    public Categorie updateCategorie(@PathVariable String id, @RequestBody Categorie categorie) {
        categorie.setId(id);
        return service.update(categorie);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategorie(@PathVariable String id) {
        service.delete(id);
    }

}
