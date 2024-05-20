package com.esgis.venteapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.esgis.venteapi.models.UserInfo;

@Repository
public interface UserRepository extends MongoRepository<UserInfo, String> {
    public UserInfo findByUsername(String username);
}
