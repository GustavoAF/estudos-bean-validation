package com.estudos.bean.validation.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import org.springframework.beans.BeanUtils;

/*
 * Como vamos precisar obter o valor de uma propriedade da classe, precisamos definir o tipo como Object 
 */
public class ValidadorFelinoDuasPatasValidator implements ConstraintValidator<ValidadorFelinoDuasPatas, Object> {

	private String especieField;
	private String valorField;
	private String especieValidar;
	
	@Override
	public void initialize(ValidadorFelinoDuasPatas constraint) {
	
		this.especieField = constraint.especieField();
		this.valorField = constraint.valorField();		
		this.especieValidar = constraint.especieValidar();
	}
	
	
	@Override
	public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
		
		boolean valido = true;

		try {
			//Reflexion usado para obter o nome do campo informado, obtendo o nome podemos buscar o valor
			//esse metoro só se faz necessário quando quisermos obter o valor de um campo, quando for o valor
			//da propriedade da anotacão isso nao é necessário.
			String especie = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(),especieField)
					.getReadMethod().invoke(objetoValidacao);
			
			if (valorField != null  && especie !=null && especieValidar != null){
				if (especie.toLowerCase().equals(especieValidar.toLowerCase())){
					if (valorField.equals("4")) {
						valido= false;
					}
				}				
			}
		} catch (Exception e) {
			throw new ValidationException(e);
		}
		
		return valido;
	}
}
