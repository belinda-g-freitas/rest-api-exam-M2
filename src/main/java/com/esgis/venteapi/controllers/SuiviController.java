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

import com.esgis.venteapi.models.Suivi;
import com.esgis.venteapi.services.SuiviService;

@RestController
@RequestMapping("/api/v1/tracking")
public class SuiviController {

  @Autowired
  private SuiviService service;

  // POST http://localhost:8080/api/tracking/new
  @PostMapping("/new")
  public Suivi create(@RequestBody Suivi suivi) {
    return service.create(suivi);
  }

  @GetMapping
  public List<Suivi> findAllSuivis() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Suivi findOneSuivis(@PathVariable String id) {
    Optional<Suivi> suivi = service.findById(id);
    if (suivi.isPresent()) {
      return suivi.get();
    }
    return null;
  }

  @PutMapping("/{id}")
  public Suivi updateSuivi(@PathVariable String id, @RequestBody Suivi suivi) {
    suivi.setId(id);
    return service.update(suivi);
  }

  // DELETE http://localhost:8080/api/tracking/12
  @DeleteMapping("/{id}")
  public void deleteSuivi(@PathVariable String id) {
    service.delete(id);
  }
}
