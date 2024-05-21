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

import com.esgis.venteapi.models.Superviser;
import com.esgis.venteapi.services.SuperviserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/supervising")
@RequiredArgsConstructor
public class SuperviserController {

    @Autowired
    private SuperviserService service;

    @PostMapping("/signup")
    public Superviser create(@RequestBody Superviser superviser) {
        return service.create(superviser);
    }

    @GetMapping
    public List<Superviser> findAllSupervisers() {
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    public Superviser findOneSupervisers(@PathVariable String id) {
        Optional<Superviser> superviser = service.findById(id);
        if (superviser.isPresent()) {
            return superviser.get();
        }
        return null;
    }

    @PutMapping("/update/{id}")
    public Superviser updateSuperviser(@PathVariable String id, @RequestBody Superviser superviser) {
        superviser.setId(id);
        return service.update(superviser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSuperviser(@PathVariable String id) {
        service.delete(id);
    }
}

