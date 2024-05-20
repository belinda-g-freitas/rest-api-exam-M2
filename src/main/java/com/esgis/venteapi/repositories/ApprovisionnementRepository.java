package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.esgis.venteapi.models.Approvisionnement;

public interface ApprovisionnementRepository extends MongoRepository<Approvisionnement, String>{

}
