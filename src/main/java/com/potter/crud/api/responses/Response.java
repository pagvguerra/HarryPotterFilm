package com.potter.crud.api.responses;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Response<T> {

	private T data;
	private List<String> erros;
	
	public Response (T data) {
		this.data = data;
	}

	public Response (List<String> erros) {
		this.erros = erros;
	}

}