package com.estudos.bean.validation.core.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidadorFelinoDuasPatasValidator.class})
public @interface ValidadorFelinoDuasPatas {
	
	String message() default "para felinos o número de patas deve ser igual a 4.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String valorField ();
	
	String especieField();
	
	String especieValidar();
	
	

}
