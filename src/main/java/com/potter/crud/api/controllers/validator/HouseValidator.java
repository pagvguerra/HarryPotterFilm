package com.potter.crud.api.controllers.validator;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.potter.crud.api.documents.Characters;
import com.potter.crud.api.documents.binding.CharacterListBinding;
import com.potter.crud.api.utils.Mappers;

public class HouseValidator {

	/**
	 * Este método recebe o Id da House dentro do objeto Character e vai 
	 * a API "www.potterapi.com" para verificar se este Id é válido
	 * 
	 * Para invocar este método é informado no UriComponentes:
	 * Scheme, Host, Path e QueryParam
	 * 
	 * A API "www.potterapi.com" exige que seja passada uma chave, 
	 * portanto esta chave é enviada através de QueryParam
	 * 
	 * No retorno da API:
	 * Caso o ID seja válido retorna true
	 * Caso o ID seja inválido retorna false
	 * Caso aconteça algum problema na integração é disparada uma exceção
	 * 
	 * @param Characters character
	 * @return boolean
	 * @throws HttpClientErrorException, HttpServerErrorException
	 * */
	public boolean idEhValido(Characters character) 
	{
		String houseId = character.getHouse();

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme(Mappers.SCHEMA)
				.host(Mappers.HOST)
				.path(Mappers.PATH)
				.queryParam(Mappers.KEY_NAME, Mappers.KEY_PASSWORD)
				.build();

		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
				
			ResponseEntity<?> resp = new RestTemplate().exchange(uri.toUriString(), HttpMethod.GET, new HttpEntity<>(null, null), List.class);
			
			ObjectMapper mapper = new ObjectMapper();
			List<CharacterListBinding> listaObjParseado = mapper.convertValue(resp.getBody(), new TypeReference<List<CharacterListBinding>>() {});
			
			for(CharacterListBinding characterListBinding : listaObjParseado) 
				if(houseId.equalsIgnoreCase(characterListBinding.getId())) 
					return true;
			
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw(e);
		}
		
		return false;
	}

}