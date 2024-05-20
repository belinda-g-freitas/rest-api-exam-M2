package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.Vente;

public interface VenteRepository extends MongoRepository<Vente, String> {

}
