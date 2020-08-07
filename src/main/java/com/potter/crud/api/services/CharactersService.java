package com.potter.crud.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.potter.crud.api.documents.Characters;
import com.potter.crud.api.repositories.CharactersRepository;

@Service
public class CharactersService implements CrudService<Characters> {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private CharactersRepository charactersRepository;
	
	/**
	 * Busca todos os Characters do banco MongoDB
	 * @return List<Characters>
	 * */
	@Override
	public List<Characters> findAll() {
		return this.charactersRepository.findAll();
	}

	/**
	 * Busca um Character do banco MongoDB por Id Informado na Requisição
	 * @param String id
	 * @return Optional<Characters>
	 * */
	@Override
	public Optional<Characters> findById(String id) {
		return this.charactersRepository.findById(id);
	}

	/**
	 * Salva um Character na base MongoDB
	 * @param Characters character
	 * @return Characters
	 * */
	@Override
	public Characters save(Characters character) {
		return this.charactersRepository.save(character);
	}

	/**
	 * Atualiza um Character na base MongoDB
	 * @param Characters character
	 * @return Characters
	 * */
	@Override
	public Characters update(Characters character) {
		return this.charactersRepository.save(character);
	}

	/**
	 * Exclui um Character do banco MongoDB por Id informado na Requisição
	 * @param String id
	 * */
	@Override
	public void deleteById(String id) {
		this.charactersRepository.deleteById(id);
	}

	/**
	 * Exclui todos os Characters do banco MongoDB
	 * @return List<Characters>
	 * */
	@Override
	public void deleteAll() {
		this.charactersRepository.deleteAll();
	}
	
	/**
	 * Busca todos os Characters do banco MongoDB por Id da House Informado na Requisição
	 * @param String houseId
	 * @return List<Characters>
	 * */
	public List<Characters> findListOfCharacterByHouseId (String houseId) {		
		Query query = new Query();
		query.addCriteria(Criteria.where("house").is(houseId));
		List<Characters> charactersByHouseIdList = mongoTemplate.find(query, Characters.class);
		return charactersByHouseIdList;
	}
	
}