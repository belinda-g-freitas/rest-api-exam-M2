package com.esgis.venteapi.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgis.venteapi.models.Role;
import com.esgis.venteapi.models.Superviseur;
import com.esgis.venteapi.repositories.SuperviseurRepository;
import com.esgis.venteapi.services.SuperviseurService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/supervisors")
public class SuperviseurController {
	@Autowired
	private SuperviseurRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private SuperviseurService service;

	@PostMapping("/new")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Superviseur supervisor) {
		Boolean exists = false;

		for (Role role : Role.values()) {
			if (supervisor.getRole().toUpperCase().equals(role.name())
					&& supervisor.getRole().toUpperCase() != "SUPERADMIN") {
				exists = true;
				break;
			}
		}
		if (!exists) {
			return ResponseEntity.badRequest().body(Map.of("message", "Not a valid role."));
		}
		//
		supervisor.setPassword(encoder.encode(supervisor.getPassword()));
		final Superviseur data = repository.save(supervisor);
		return new ResponseEntity<>(Map.of("message", "Success", "data", data), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public List<Superviseur> findAllSuperviseurs() {
		return service.findAll();
	}

	@GetMapping("/find/{id}")
	public Superviseur findOneSuperviseurs(@PathVariable String id) {
		Optional<Superviseur> superviseur = service.findById(id);
		if (superviseur.isPresent()) {
			return superviseur.get();
		}
		return null;
	}

	@PutMapping("/update/{id}")
	public Superviseur updateSuperviseur(@PathVariable String id, @RequestBody Superviseur superviseur) {
		Optional<Superviseur> optional = service.findById(id);

		if (optional.isPresent()) {
			Superviseur data = optional.get();
			data.setId(id);
			data.setRole(Role.USER.name());
			data.setUsername(data.getUsername());

			if (superviseur.getNomSuperviseur() != null) {
				data.setNomSuperviseur(superviseur.getNomSuperviseur());
			}
			if (superviseur.getPrenomSuperviseur() != null) {
				data.setPrenomSuperviseur(superviseur.getPrenomSuperviseur());
			}
			if (superviseur.getPassword() != null) {
				data.setPassword(superviseur.getPassword());
			}
			
			return service.update(data);
		} else {
			return null;
		}
	}

	@DeleteMapping("/delete/{id}")
	public void deleteSuperviseur(@PathVariable String id) {
		service.delete(id);
	}
}
