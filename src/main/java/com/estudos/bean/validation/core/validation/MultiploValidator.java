package com.estudos.bean.validation.core.validation;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * Esta classe representa a implementação da nossa anotação, lembrando que obrigatoriamente esta deve implementar
 * ConstraintValidator passando os parametros da nossa anotação e o tipo.
 */
public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {
	
	private int numeroMultiplo;
	
	/*
	 * metodo não obrigatório usado para iniciar o valor recebido pela anotação
	 */
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		
		this.numeroMultiplo = constraintAnnotation.numero();
	}
	
	/*
	 * Método obrigatório para ser reescrito neste deve ser implementado a lógica da implementação  
	 */
	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		
		boolean valido = true;
		
		if (value != null) {
			BigDecimal valorDecimal = BigDecimal.valueOf(value.doubleValue());
			BigDecimal multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
			BigDecimal resto = valorDecimal.remainder(multiploDecimal);
			
			valido = BigDecimal.ZERO.compareTo(resto) == 0;
		}
		return valido;
	}
	
	

}
