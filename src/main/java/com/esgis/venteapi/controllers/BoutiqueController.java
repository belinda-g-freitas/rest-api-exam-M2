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

import com.esgis.venteapi.models.Boutique;
import com.esgis.venteapi.services.BoutiqueService;

@RestController
@RequestMapping("/api/v1/stores")
public class BoutiqueController {

    @Autowired
    private BoutiqueService service;

    //POST http://localhost:8080/api/stores/signup
    @PostMapping("/signup")
    public Boutique create(@RequestBody Boutique store) {
        return service.create(store);
    }

    @GetMapping
    public List<Boutique> findAllBoutiques() {
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    public Boutique findOneBoutiques(@PathVariable String id) {
        Optional<Boutique> store = service.findById(id);
        if (store.isPresent()) {
            return store.get();
        }
        return null;
    }

    @PutMapping("/update/{id}")
    public Boutique updateBoutique(@PathVariable String id, @RequestBody Boutique store) {
        store.setId(id);
        return service.update(store);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBoutique(@PathVariable String id) {
        service.delete(id);
    }

}
