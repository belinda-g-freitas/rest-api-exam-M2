package com.esgis.venteapi.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgis.venteapi.models.Produit;
import com.esgis.venteapi.models.Vente;
import com.esgis.venteapi.services.ProduitService;
import com.esgis.venteapi.services.VenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/selling")
public class VenteController {

  @Autowired
  private VenteService service;

  @Autowired
  private ProduitService prodService;

  @PostMapping("/new")
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Vente vente) {
    if (vente.getDateVente().after(new Date(System.currentTimeMillis())) ||
        vente.getQteVendue() < 1) {
      return ResponseEntity.badRequest().body(Map.of("message", "Invalid dateVente or qteVendue."));
    }
    //
    final Optional<Produit> store = prodService.findById(vente.getProduitId());
    if (store == null) {
      return ResponseEntity.badRequest().body(Map.of("message", "This product doesn't exist."));
    }
    //
    final Vente data = service.create(vente);
    return new ResponseEntity<>(Map.of("message", "Success", "data", data), HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public List<Vente> findAllVentes() {
    return service.findAll();
  }

  @GetMapping("/find/{id}")
  public Vente findOneVentes(@PathVariable String id) {
    Optional<Vente> vente = service.findById(id);
    if (vente.isPresent()) {
      return vente.get();
    }
    return null;
  }

  @PutMapping("/update/{id}")
  public Vente updateVente(@PathVariable String id, @RequestBody Vente vente) {
    Optional<Vente> optional = service.findById(id);

    if (optional.isPresent()) {
      Vente data = optional.get();
      data.setId(id);

      if (vente.getDateVente() != null) {
        data.setDateVente(vente.getDateVente());
      }
      if (vente.getProduitId() != null) {
        data.setProduitId(vente.getProduitId());
      }
      if (vente.getQteVendue() > 0) {
        data.setQteVendue(vente.getQteVendue());
      }

      return service.update(data);
    } else {
      return null;
    }
  }

  @DeleteMapping("/delete/{id}")
  public void deleteVente(@PathVariable String id) {
    service.delete(id);
  }
}
