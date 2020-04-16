package com.estudos.bean.validation.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/*
 * Esta é uma anotação criado pelo programador para isso devemos anotar com as seguintes anotações:
 * @Target representa onde nossa anotação podera ser usada
 * @Retention representa em qual momento ela será executada, no nosso caso em RunTime ou seja tempo de execuçao
 * @Constraint representa na propriedade validatedBy qual classe irá conter a implementação da logica
 * 
 * Nesse Constraint implementamos 3 propriedades default que são:
 * message: mensagem padrão que seja lancada, pode ser tratada como no message.properties
 * grops: incluir o tratamento de Grupos de Constraints
 * Payload: para uso de path
 * 
 * Além dessas 3 criamos uma própria que será a nossa implementação
 */

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {MultiploValidator.class})
public @interface Multiplo {
	
	String message() default "multiplo inválido.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	int numero();

}
