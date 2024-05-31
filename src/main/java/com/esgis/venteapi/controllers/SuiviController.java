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

import com.esgis.venteapi.models.AgentVendeur;
import com.esgis.venteapi.models.Boutique;
import com.esgis.venteapi.models.Suivi;
import com.esgis.venteapi.services.AgentVendeurService;
import com.esgis.venteapi.services.BoutiqueService;
import com.esgis.venteapi.services.SuiviService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tracking")
public class SuiviController {

  @Autowired
  private SuiviService service;

  @Autowired
	private BoutiqueService storeService;

  @Autowired
  private AgentVendeurService sellerService;

  @PostMapping("/new")
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Suivi suivi) {
    if (suivi.getDebutSuivi().after(new Date(System.currentTimeMillis()))
        || suivi.getFinSuivi().before(new Date(System.currentTimeMillis()))) {
      return ResponseEntity.badRequest().body(Map.of("message", "Invalid dates."));
    }
    //
		final Optional<Boutique> store = storeService.findById(suivi.getStoreId());
		if (store == null) {
			return ResponseEntity.badRequest().body(Map.of("message", "This store doesn't exist."));
		}
    //
    final Optional<AgentVendeur> seller = sellerService.findById(suivi.getSellerId());
    if (seller == null) {
      return ResponseEntity.badRequest().body(Map.of("message", "This seller doesn't exist."));
    }
    //
    final Suivi data = service.create(suivi);
    return new ResponseEntity<>(Map.of("message", "Success", "tracking", data), HttpStatus.CREATED);
  }

  @GetMapping
  public List<Suivi> findAllSuivis() {
    return service.findAll();
  }

  @GetMapping("/find/{id}")
  public Suivi findOneSuivis(@PathVariable String id) {
    Optional<Suivi> suivi = service.findById(id);
    if (suivi.isPresent()) {
      return suivi.get();
    }

    return null;
  }

  @PutMapping("/update/{id}")
  public Suivi updateSuivi(@PathVariable String id, @RequestBody Suivi suivi) {
    suivi.setId(id);
    return service.update(suivi);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteSuivi(@PathVariable String id) {
    service.delete(id);
  }
}
