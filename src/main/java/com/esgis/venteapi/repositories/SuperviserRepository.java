package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.Superviser;

public interface SuperviserRepository extends MongoRepository<Superviser, String>{

}
