package com.esgis.venteapi.controllers;

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
import com.esgis.venteapi.models.Role;
import com.esgis.venteapi.repositories.AgentVendeurRepository;
import com.esgis.venteapi.services.AgentVendeurService;

import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/v1/sellers")
public class AgentVendeurController {
  @Autowired
  private AgentVendeurRepository repository;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private AgentVendeurService service;

  @PostMapping("/signup")
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody AgentVendeur seller) {
    seller.setPassword(encoder.encode(seller.getPassword()));
    seller.setRole(Role.USER.name());
    
    final AgentVendeur data = repository.save(seller);
    return new ResponseEntity<>(Map.of("message", "Success", "seller", data), HttpStatus.CREATED);
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
    seller.setRole(Role.USER.name());
    return service.update(seller);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteAgentVendeur(@PathVariable String id) {
    service.delete(id);
  }

}
