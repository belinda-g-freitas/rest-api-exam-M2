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

import com.esgis.venteapi.models.Boutique;
import com.esgis.venteapi.models.Categorie;
import com.esgis.venteapi.models.Produit;
import com.esgis.venteapi.services.BoutiqueService;
import com.esgis.venteapi.services.CategorieService;
import com.esgis.venteapi.services.ProduitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProduitController {

	@Autowired
	private ProduitService service;

	@Autowired
	private CategorieService catService;

	@Autowired
	private BoutiqueService storeService;

	@PostMapping("/new")
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Produit product) {
		final Optional<Categorie> cat = catService.findById(product.getCategoryId());
		if (cat == null) {
			return ResponseEntity.badRequest().body(Map.of("message", "This category doesn't exist."));
		}

		final Optional<Boutique> store = storeService.findById(product.getStoreId());
		if (store == null) {
			return ResponseEntity.badRequest().body(Map.of("message", "This store doesn't exist."));
		}
		//
		final Produit data = service.create(product);
		return new ResponseEntity<>(Map.of("message", "Success", "data", data), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public List<Produit> findAllProduits() {
		return service.findAll();
	}

	@GetMapping("/find/{id}")
	public Produit findOneProduits(@PathVariable String id) {
		Optional<Produit> produit = service.findById(id);
		if (produit.isPresent()) {
			return produit.get();
		}
		return null;
	}

	@PutMapping("/update/{id}")
	public Produit updateProduit(@PathVariable String id, @RequestBody Produit produit) {
		produit.setId(id);
		return service.update(produit);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteProduit(@PathVariable String id) {
		service.delete(id);
	}

}
