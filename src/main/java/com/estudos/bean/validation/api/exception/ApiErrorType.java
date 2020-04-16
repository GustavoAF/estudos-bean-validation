package com.estudos.bean.validation.api.exception;

import lombok.Getter;

@Getter
public enum ApiErrorType {
	
	//Enum criado para facilitar a instanciação da classe ApiError, informando o tipo do erro com o titulo e a url da documentação
	
	VALOR_INVALIDO_INFORMADO("/valor-invalido","Valor Inválido") ;
	
	private String title;
	private String path;
	
	ApiErrorType(String path, String title){
		this.path = "Https://meusite.com.br/api/documentacao" + path;
	    this.title = title;
		
	}

}
