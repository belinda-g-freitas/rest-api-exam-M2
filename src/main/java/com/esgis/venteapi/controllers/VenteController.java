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

import com.esgis.venteapi.models.Vente;
import com.esgis.venteapi.services.VenteService;

@RestController
@RequestMapping("/api/v1/selling")
public class VenteController {

  @Autowired
  private VenteService service;

  // POST http://localhost:8080/api/selling/new
  @PostMapping("/new")
  public Vente create(@RequestBody Vente vente) {
    return service.create(vente);
  }

  @GetMapping
  public List<Vente> findAllVentes() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Vente findOneVentes(@PathVariable String id) {
    Optional<Vente> vente = service.findById(id);
    if (vente.isPresent()) {
      return vente.get();
    }
    return null;
  }

  @PutMapping("/{id}")
  public Vente updateVente(@PathVariable String id, @RequestBody Vente vente) {
    vente.setId(id);
    return service.update(vente);
  }

  // DELETE http://localhost:8080/api/selling/12
  @DeleteMapping("/{id}")
  public void deleteVente(@PathVariable String id) {
    service.delete(id);
  }
}
