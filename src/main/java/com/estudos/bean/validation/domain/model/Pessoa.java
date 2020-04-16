package com.estudos.bean.validation.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import lombok.Data;
/*
 * Link contendo todos os Bean Validations
 * https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints
 */
@Data
@Table
@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	/*
	 * Umas das formas de se organizar as constraints de validação, seria organiza-las em grupos, sendo possível definir as validações somente
	 * para determinadas classes. Para isso é preciso criar um interface (nesse exemplo definida como "Groups") e dentro dela uma nova interface
	 * (nesse exemplo definida como CadastroPessoa). Feito isso na propriedade groups de cada constrait defina a interface criada. Importante lembrar
	 * tambem que no controlador é preciso mudar DE @Valid para @Validated. O exemplo do uso de groups ficaria assim @NotNull(groups = CadastroPessoa.class)
	 */
	
	/*
	 * Uma outra forma de enviar as mensagens é informa-las direto na constraits usando a propriedade message da anotação
	 * conforme exemplo abaixo
	 */
	@NotBlank(message = "Nome da Pessoa não pode ser informado em branco.")
	/*
	 * Outra forma de obter as mensagens personalizadas das constraints é usando o arquivo message.properties mas importante ressaltar
	 * que por padrão, esse deve ser o nome do arquivo. Caso esse arquivo exista e sua constraints estiver informada no mesmo, o spring deve
	 * ignorar qualquer outro tipo de informação e usar este.
	 */
	@NotNull
	String nome;
		
	@NotNull
	String sobrenome;
}
