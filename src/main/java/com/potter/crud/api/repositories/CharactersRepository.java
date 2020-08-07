package com.potter.crud.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.potter.crud.api.documents.Characters;

public interface CharactersRepository extends MongoRepository<Characters, String> {

}
