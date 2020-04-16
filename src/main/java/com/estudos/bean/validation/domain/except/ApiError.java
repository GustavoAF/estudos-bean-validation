package com.estudos.bean.validation.domain.except;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/* 
 * Classe para atender a RFC 7807 - Problem Details - Esta define o modelo de retorno contendo as informações sobre
 * a exception gerada. os campos abaixo status, type, title e opcionalmente userMessage, definem as informações que 
 * devem ser retornada em caso de falha por qualquer mótivo. Desses somente o campo userMessage é considerado como opcional.
 */

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ApiError {
	
	private Integer status;
	private String type;
	private String title;
	private String detail;
	private List<Object> objects;
	
/*
 * Os campos da innerclass abaixo devem ser declarados como uma lista na classe acima, esses devem informar quais campos
 * sofreram as validações.
 */
	@Getter
	@Builder
	public static class  Object{
		
		private String name;
		private String userMesage;
	}

}