package com.potter.crud.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potter.crud.api.controllers.validator.HouseValidator;
import com.potter.crud.api.documents.Characters;
import com.potter.crud.api.responses.Response;
import com.potter.crud.api.responses.ResponseMessage;
import com.potter.crud.api.services.CharactersService;
import com.potter.crud.api.utils.Mappers;
import com.potter.crud.api.utils.Messages;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Classe Controller para Requisições Rest
 * Esta classe está toda mapeada com SWAGGER 
 * para geração automática de documentação das 
 * APIs REST
 * 
 * Mapeamentos:
 * RestController, RequestMapping, Api e CrossOrigin
 * */
@RestController
@RequestMapping(path = Mappers.API_CHARACTER)
@Api(value = Messages.SWAGGER_MSG_CONTROLER_INIT)
@CrossOrigin(origins = "*") 
public class CharactersController {

	@Autowired
	private CharactersService characterService;
	

	/**
	 * Método REST para Retornar um Character por um Character ID enviado na Requisição
	 * O retorno deste método será um JSON com os dados do Character ou então, caso o 
	 * Character não seja encontrado, será um JSON informando a seguinte mesagem:
	 * "Não foi possível encontrar nenhum Character com o Id Character fornecido"
	 * 
	 * Mapeamentos:
	 * GetMapping, ApiOperation
	 * 
	 * @param String id
	 * @return ResponseEntity<ResponseMessage<Characters>>
	 * */
	@GetMapping(value = Mappers.FIND_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = Messages.SWAGGER_MSG_FIND_BY_ID)
	public ResponseEntity<ResponseMessage<Characters>> findById(@PathVariable(name = Mappers.ID_PARAM) String id) {
		Optional<Characters> characterValido = this.characterService.findById(id);
		if (!characterValido.isPresent())
			return ResponseEntity.badRequest().body(new ResponseMessage<Characters>(Messages.CHARACTER_NOT_FOUND_BY_CHARACTER_ID));
		return ResponseEntity.ok(new ResponseMessage<Characters>(characterValido.get()));
	}
	
	
	/**
	 * Método REST para Retornar a lista completa com todos os Characters
	 * O retorno deste método será um JSON com uma lista de todos os Character ou então, caso o 
	 * nenhuma Character seja encontrado, será um JSON informando a seguinte mesagem:
	 * "Nenhum Character encontrado"
	 * 
	 * Mapeamentos:
	 * GetMapping, ApiOperation
	 * 
	 * @return ResponseEntity<ResponseMessage<List<Characters>>>
	 * */
	@GetMapping(value = Mappers.FIND_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = Messages.SWAGGER_MSG_FIND_ALL)
	public ResponseEntity<ResponseMessage<List<Characters>>> findAll() {
		List<Characters> lista = this.characterService.findAll();
		return apresentaORetornoComListaOuComMensagemDeErro(lista, Messages.NONE_CHARACTER_FOUND);
	}
	

	/**
	 * Método REST para retornar uma lista de Character por uma House ID enviada na Requisição
	 * O retorno deste método será um JSON com uma lista de dados de Character por House ID ou então, caso o 
	 * nenhum Character seja encontrado, será um JSON informando a seguinte mesagem:
	 * "Nenhum character encontrado com o Id da casa fornecida"
	 * 
	 * Mapeamentos:
	 * GetMapping, ApiOperation
	 * 
	 * @param String id
	 * @return ResponseEntity<ResponseMessage<List<Characters>>>
	 * */
	@GetMapping(value = Mappers.ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = Messages.SWAGGER_FIND_LIST_OF_CHARACTER_BY_HOUSE_ID)
	public ResponseEntity<ResponseMessage<List<Characters>>> findListOfCharacterByHouseId(@PathVariable(name = Mappers.ID_PARAM) String id) {
		List<Characters> lista = this.characterService.findListOfCharacterByHouseId(id);
		return apresentaORetornoComListaOuComMensagemDeErro(lista, Messages.NONE_CHARACTER_FOUND_BY_HOUSE_ID);
	}

	
	/**
	 * Método REST para remover um Character por um Character ID enviada na Requisição
	 * O retorno deste método será um OK para esta requisição (HTTP 200 statusCode) 
	 * 
	 * Mapeamentos:
	 * DeleteMapping, ApiOperation
	 * 
	 * @param String id
	 * @return ResponseEntity<Characters>
	 * */
	@DeleteMapping(value = Mappers.DELETE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = Messages.SWAGGER_DELETE_BY_ID)
	public ResponseEntity<Characters> remove(@PathVariable(name = Mappers.ID_PARAM) String id) {
		this.characterService.deleteById(id);
		return ResponseEntity.ok().build();
	}


	/**
	 * Método REST para remover todos os Characters
	 * O retorno deste método será um OK para esta requisição (HTTP 200 statusCode) 
	 * 
	 * Mapeamentos:
	 * DeleteMapping, ApiOperation
	 * 
	 * @return ResponseEntity<Characters>
	 * */
	@DeleteMapping(value = Mappers.DELETE_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = Messages.SWAGGER_DELETE_ALL)
	public ResponseEntity<Characters> removeAll() {
		this.characterService.deleteAll();
		return ResponseEntity.ok().build();
	}
	
	
	/**
	 * Método REST para salvar um Character
	 * O retorno deste método será um JSON com os dados do Character cadastrado, ou caso 
	 * os parametros obrigatórios de requisição não sejam enviados, mensagens de erros são retornadas:
	 * 	"Name não pode ser vazio";
	 *	"Role não pode ser vazio";
	 *	"House não pode ser vazio";
	 *	"Patronus não pode ser vazio";
	 *	"School não pode ser vazio";
	 *
	 * Mapeamentos:
	 * PostMapping, ApiOperation
	 * 
	 * @param Characters character
	 * @param BindingResult result
	 * @return ResponseEntity<Characters>
	 * */
	@PostMapping(value = Mappers.SAVE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = Messages.SWAGGER_SAVE)
	public ResponseEntity<Response<Characters>> save(@Valid @RequestBody Characters character, BindingResult result) {
		return persist(null, character, result, false);
	}

	
	/**
	 * Método REST para atualizar um Character
	 * O retorno deste método será um JSON com os dados do Character atualizado, ou caso 
	 * os parametros obrigatórios de requisição não sejam enviados, mensagens de erros são retornadas:
	 * 	"Name não pode ser vazio";
	 *	"Role não pode ser vazio";
	 *	"House não pode ser vazio";
	 *	"Patronus não pode ser vazio";
	 *	"School não pode ser vazio";
	 *
	 * Mapeamentos:
	 * PutMapping, ApiOperation
	 *
	 * @param String id
	 * @param Characters character
	 * @param BindingResult result
	 * @return ResponseEntity<Characters>
	 * */
	@PutMapping(value = Mappers.UPDATE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = Messages.SWAGGER_UPDATE)
	public ResponseEntity<Response<Characters>> update(@PathVariable(name = Mappers.ID_PARAM) String id, @Valid @RequestBody Characters character, BindingResult result) {
		return persist(id, character, result, true);
	}

	
	/**
	 * Método invocado pelos métodos save e update para validar o ID da House informado na requisição
	 * e caso esteja válido faz a persistência na base.
 	 *
 	 * Caso inválido, uma mensagem de erro é enviada no JSON:
 	 * Id da House inválido
	 *
	 * Caso seja válido então é retornado um JSON com o Character persistido
	 * 
	 * @param String id
	 * @param Characters character
	 * @param BindingResult result
	 * @param boolean ehUpdate
	 * @return ResponseEntity<Characters>
	 * */
	private ResponseEntity<Response<Characters>> persist(String id, Characters character, BindingResult result,	boolean ehUpdate) {
		List<String> erros = new ArrayList<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Characters>(erros));
		}

		if (new HouseValidator().idEhValido(character)) {
			if (ehUpdate) 
				return realizaUpdateComIdInformadoValidoOuApresentaMsgDeErroCasoIdInvalido(id, character, erros);
			else
				return ResponseEntity.ok(new Response<Characters>(this.characterService.save(character)));
		} else {
			erros.add(Messages.INVALID_HOUSE_ID);
			return ResponseEntity.badRequest().body(new Response<Characters>(erros));
		}
	}

	
	/**
	 * Método invocado pelo método update para buscar o Character com o ID Character informado na requisição
	 *
 	 * Caso não encontre o Character, então uma mensagem de erro é enviada no JSON:
 	 * Não foi possível encontrar nenhum Character com o Id Character fornecido
	 *
	 * Caso seja encontrado então persiste e é retornado um JSON com o Character persistido
	 * 
	 * @param String id
	 * @param Characters character
	 * @param List<String> erros
	 * @return ResponseEntity<Response<Characters>>
	 * */
	private ResponseEntity<Response<Characters>> realizaUpdateComIdInformadoValidoOuApresentaMsgDeErroCasoIdInvalido(String id, Characters character, List<String> erros) {
		Optional<Characters> characterValido = this.characterService.findById(id);
		if (characterValido.isPresent()) {
			character.setId(id);
			return ResponseEntity.ok(new Response<Characters>(this.characterService.save(character)));
		} else {
			erros.add(Messages.CHARACTER_NOT_FOUND_BY_CHARACTER_ID);
			return ResponseEntity.badRequest().body(new Response<Characters>(erros));
		}
	}

	
	/**
	 * Método invocado pelo métodos find() e findAll()
 	 * Retorna mensagem de erro caso não encontre nenhum registro ou retorna os registros, caso existam
	 * 
	 * @param List<Characters> lista
	 * @param String msg
	 * @return ResponseEntity<ResponseMessage<List<Characters>>>
	 * */
	private ResponseEntity<ResponseMessage<List<Characters>>> apresentaORetornoComListaOuComMensagemDeErro(List<Characters> lista, String msg) {
		if (lista.isEmpty()) 
			return ResponseEntity.badRequest().body(new ResponseMessage<List<Characters>>(msg));

		return ResponseEntity.ok(new ResponseMessage<List<Characters>>(lista));
	}

}