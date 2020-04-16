package com.estudos.bean.validation.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration class ValidationConfig {
	
	/*
	 * A classe LocalValidatorFactoryBean é uma classe que faz a integração e configuração do bean validation
	 * com o spring.
	 * Precisamos dessa classe de configuração para casos onde é necessario unir mensagens dos arquivos 
	 * ValidationMessages.properties do bean validation e do messages.properties do Spring.
	 * Assim para obter as mensagens podemos usar o padrão dos dois tipos.
	 */
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource message) {
		
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(message);
		
		return bean;
		
	}
}
