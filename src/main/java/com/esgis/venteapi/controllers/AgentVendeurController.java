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

import com.esgis.venteapi.models.AgentVendeur;
import com.esgis.venteapi.services.AgentVendeurService;

@RestController
@RequestMapping("/api/v1/sellers")
public class AgentVendeurController {

  @Autowired
  private AgentVendeurService service;

  // POST http://localhost:8080/api/sellers/new
  @PostMapping("/new")
  public AgentVendeur create(@RequestBody AgentVendeur seller) {
    return service.create(seller);
  }

  @GetMapping
  public List<AgentVendeur> findAllAgentVendeurs() {
    return service.findAll();
  }

  @GetMapping("/find/{id}")
  public AgentVendeur findOneAgentVendeurs(@PathVariable String id) {
    Optional<AgentVendeur> seller = service.findById(id);
    if (seller.isPresent()) {
      return seller.get();
    }
    return null;
  }

  @PutMapping("/update/{id}")
  public AgentVendeur updateAgentVendeur(@PathVariable String id, @RequestBody AgentVendeur seller) {
    seller.setId(id);
    return service.update(seller);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteAgentVendeur(@PathVariable String id) {
    service.delete(id);
  }

}
