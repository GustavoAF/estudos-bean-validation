package com.estudos.bean.validation.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.estudos.bean.validation.core.validation.Multiplo;
import com.estudos.bean.validation.core.validation.ValidadorFelinoDuasPatas;

import lombok.Data;

@ValidadorFelinoDuasPatas(especieField = "especie",valorField = "4",especieValidar = "Felino")
@Table
@Entity
@Data
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	/*
	 * Usando Resource Bundle do bean validation
	 */
	@Min(value=2)
	@Multiplo(numero = 2)
	Integer qtdePatas;
	
	@NotNull
	String especie;
	
	/*
	 * usando um identificar para obter a mensagem. maiores informações no arquivo message.properties
	 */
	@Positive(message = "{Valor.invalido}")
	Long valor;

}
