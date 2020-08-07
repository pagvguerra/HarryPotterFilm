package com.potter.crud.api.documents.binding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_id", "name", "mascot", "headOfHouse", "houseGhost", "founder", "__v", "school", "members", "values", "colors" })
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CharacterListBinding {

	@JsonProperty("_id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("mascot")
	private String mascot;
	
	@JsonProperty("headOfHouse")
	private String headOfHouse;

	@JsonProperty("houseGhost")
	private String houseGhost;
	
	@JsonProperty("founder")
	private String founder;
	
	@JsonProperty("__v")
	private Integer v;

	@JsonProperty("school")
	private String school;

	@JsonProperty("members")
	private List<String> members = null;
	
	@JsonProperty("values")
	private List<String> values = null;
	
	@JsonProperty("colors")
	private List<String> colors = null;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}