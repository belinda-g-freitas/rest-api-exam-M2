package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.Suivi;

public interface SuiviRepository extends MongoRepository<Suivi, String> {

}
