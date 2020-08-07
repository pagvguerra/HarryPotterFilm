package com.potter.crud.api.documents;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.potter.crud.api.utils.Messages;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Characters {

	@Id
	@EqualsAndHashCode.Include
	private String id;

	@NotEmpty(message = Messages.NAME_CANNOT_BE_EMPTY)
	private String name;
	
	@NotEmpty(message = Messages.ROLE_CANNOT_BE_EMPTY)
	private String role;
	
	@NotEmpty(message = Messages.HOUSE_CANNOT_BE_EMPTY)
	private String house;
	
	@NotEmpty(message = Messages.PATRONUS_CANNOT_BE_EMPTY)
	private String patronus;
	
	@NotEmpty(message = Messages.SCHOOL_CANNOT_BE_EMPTY)
	private String school;
	
	private String species;
	private String bloodStatus;
	private String alias;
	private String wand;
	private String boggart;
	private String animagus;
	private boolean deathEater;
	private boolean dumbledoresArmy;
	private boolean orderOfThePhoenix;
	private boolean ministryOfMagic;

}