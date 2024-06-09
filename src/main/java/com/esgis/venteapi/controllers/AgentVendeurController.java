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

  @PostMapping("/new")
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody AgentVendeur seller) {
    seller.setPassword(encoder.encode(seller.getPassword()));
    seller.setRole(Role.USER.name());

    final AgentVendeur data = repository.save(seller);
    return new ResponseEntity<>(Map.of("message", "Success", "data", data), HttpStatus.CREATED);
  }

  @GetMapping("/all")
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
    Optional<AgentVendeur> optional = service.findById(id);

    if (optional.isPresent()) {
      AgentVendeur data = optional.get();
      data.setId(id);
      data.setRole(Role.USER.name());
      data.setUsername(data.getUsername());

      if (seller.getNomAgent() != null) {
        data.setNomAgent(seller.getNomAgent());
      }
      if (seller.getPassword() != null) {
        data.setPassword(seller.getPassword());
      }
      if (seller.getPrenomAgent() != null) {
        data.setPrenomAgent(seller.getPrenomAgent());
      }
      return service.update(data);
    } else {
      return null;
    }
  }

  @DeleteMapping("/delete/{id}")
  public void sellerAgentVendeur(@PathVariable String id) {
    service.delete(id);
  }

}
