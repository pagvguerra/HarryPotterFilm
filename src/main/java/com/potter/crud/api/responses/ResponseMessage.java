package com.potter.crud.api.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ResponseMessage<T> {

	private T data;
	private String erros;
	
	public ResponseMessage(T data) {
		this.data = data;
	}
	
	public ResponseMessage (String erros) {
		this.erros = erros;
	}
	
}