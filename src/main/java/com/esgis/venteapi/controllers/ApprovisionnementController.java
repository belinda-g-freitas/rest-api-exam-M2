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

import com.esgis.venteapi.models.Approvisionnement;
import com.esgis.venteapi.services.ApprovisionnementService;

@RestController
@RequestMapping("/api/v1/supplies")
public class ApprovisionnementController {

    @Autowired
    private ApprovisionnementService service;

    //POST http://localhost:8080/api/supplies/new
    @PostMapping("/new")
    public Approvisionnement create(@RequestBody Approvisionnement supply) {
        return service.create(supply);
    }

    @GetMapping
    public List<Approvisionnement> findAllApprovisionnements() {
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    public Approvisionnement findOneApprovisionnements(@PathVariable String id) {
        Optional<Approvisionnement> supply = service.findById(id);
        if (supply.isPresent()) {
            return supply.get();
        }
        return null;
    }

    @PutMapping("/update/{id}")
    public Approvisionnement updateApprovisionnement(@PathVariable String id, @RequestBody Approvisionnement supply) {
        supply.setId(id);
        return service.update(supply);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteApprovisionnement(@PathVariable String id) {
        service.delete(id);
    }

}

