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

import com.esgis.venteapi.models.Approvisionnement;
import com.esgis.venteapi.models.Boutique;
import com.esgis.venteapi.models.Role;
import com.esgis.venteapi.repositories.BoutiqueRepository;
import com.esgis.venteapi.services.BoutiqueService;

import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/v1/stores")
public class BoutiqueController {
	@Autowired
	private BoutiqueRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private BoutiqueService service;

	@PostMapping("/new")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Boutique store) {
		if (store.getTelBoutique().length() != 8 || !store.getTelBoutique().matches("\\d+")) {
			return ResponseEntity.badRequest().body(Map.of("message", "Invalid telBoutique."));
		}

		store.setPassword(encoder.encode(store.getPassword()));
		store.setRole(Role.USER.name());

		final Boutique data = repository.save(store);
		return new ResponseEntity<>(Map.of("message", "Success", "store", data), HttpStatus.CREATED);
	}

	@GetMapping("/all")
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
		store.setRole(Role.USER.name());
		return service.update(store);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteBoutique(@PathVariable String id) {
		service.delete(id);
	}

}
