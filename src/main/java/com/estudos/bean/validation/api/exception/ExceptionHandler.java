package com.estudos.bean.validation.api.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.estudos.bean.validation.domain.except.ApiError;

/*
 * Esta classe é a cereja do bolo do tratamento de Constraints
 * Basicamente quando usamos uma constraint de validação e a mesma não é atendida será levantada uma exception. 
 * Por esse motivo usamos uma ExceptionHandler para interceptar a excessão e exibir uma resposta no padrão da RFC 7807 - Problem Details.
 * Além disso, conseguimos obter detalhes extraindo a exception gerada MethodArgumentNotValidException, podendo informar de forma precisa
 * qual validação foi executada.
 */

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		//Este bloco trata a exception e a especifica criando os campos necessários para retonrnar
		ApiErrorType apiErrorType = ApiErrorType.VALOR_INVALIDO_INFORMADO;
		String detail = "Os valores informados não são válidos, por favor verifique."; 
		
		//Com a exeption informada, o bloco abaixo consegue extrair exatamente os campos que foram validados pela constraint
		//retornando uma lista para caso seja mais de um.
		BindingResult  bindingResult =  ex.getBindingResult();
		List<ApiError.Object> problemFields = bindingResult.getAllErrors().stream()
				.map(objectdError -> {
				
				//variável abaixo foi criada para obter exatamente o campo que causou a exception.
				String message = messageSource.getMessage(objectdError, LocaleContextHolder.getLocale());
				
				String name = objectdError.getObjectName();
				//Este if se faz necessário para tratar o nome do field quando é uma anotação criada pelo programador
				// anotações a nivel de classe, esta não impacta em nada os tratamentos anteriores
				if (objectdError instanceof FieldError) {
					name = ((FieldError) objectdError).getField();
				}
				
				return ApiError.Object.builder()
				.name(name)
				.userMesage(message)
				.build();
				})
				.collect(Collectors.toList());
		
		//Já com o objeto tratado o mesmo é enviado para o método handleExceptionInternal sendo passado no body da resposata
		ApiError apiError = CreateErrorApiBuilder(status, apiErrorType, detail)
				.objects(problemFields)
				.build();
		
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
		
	}
	
	//Metodo handleExceptionInternal invocando o método handleExceptionInternal da classe pai.
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	/*
	 * Método usado para facilitar o uso do modelo do body este apenas retorna uma instancia de um Builder
	 * isso se faz necessário para que seja possível incluir alguma nova informação depois do retorno desse método
	 * pois o mesmo não retorna um objeto pronto, e sim um  ApiErrorBuilder.
	 */
	private ApiError.ApiErrorBuilder CreateErrorApiBuilder(HttpStatus status, ApiErrorType errorType, String detail) {		
		return ApiError.builder()
				.status(status.value())
				.type(errorType.getPath())
				.title(errorType.getTitle())
				.detail(detail);
	}


}
