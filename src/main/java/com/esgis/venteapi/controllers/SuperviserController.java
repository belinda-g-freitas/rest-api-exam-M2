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
import com.esgis.venteapi.models.Superviser;
import com.esgis.venteapi.models.Superviseur;
import com.esgis.venteapi.services.AgentVendeurService;
import com.esgis.venteapi.services.SuperviserService;
import com.esgis.venteapi.services.SuperviseurService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/supervising")
@RequiredArgsConstructor
public class SuperviserController {

	@Autowired
	private SuperviserService service;
	@Autowired
	private AgentVendeurService sellerService;
	@Autowired
	private SuperviseurService supService;

	@PostMapping("/new")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Superviser superviser) {
		if (superviser.getDebutSupervision().after(new Date(System.currentTimeMillis()))
				|| superviser.getFinSupervision().before(new Date(System.currentTimeMillis()))) {
			return ResponseEntity.badRequest().body(Map.of("message", "Error, missing resuired fields or invalid values."));
		}
		//
		final Optional<AgentVendeur> seller = sellerService.findById(superviser.getAgentId());
		if (seller == null) {
			return ResponseEntity.badRequest().body(Map.of("message", "This seller doesn't exist."));
		}
		//
		final Optional<Superviseur> supervis = supService.findById(superviser.getSuperviseurId());
		if (supervis == null) {
			return ResponseEntity.badRequest().body(Map.of("message", "This supervisor doesn't exist."));
		}
		//
		final Superviser data = service.create(superviser);
		return new ResponseEntity<>(Map.of("message", "Success", "supervising", data), HttpStatus.CREATED);
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
