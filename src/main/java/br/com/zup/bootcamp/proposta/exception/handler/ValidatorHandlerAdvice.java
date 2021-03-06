package br.com.zup.bootcamp.proposta.exception.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.bootcamp.proposta.exception.ApiErroException;
import br.com.zup.bootcamp.proposta.exception.MensagemPadronizada;
import feign.FeignException;

@RestControllerAdvice
public class ValidatorHandlerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MensagemPadronizada> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
		Collection<String> mensagens = new ArrayList<>();
		BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		fieldErrors.forEach(fieldError -> {
			String message = String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
			mensagens.add(message);
		});

		MensagemPadronizada erroPadronizado = new MensagemPadronizada(mensagens);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadronizado);
	}

	@ExceptionHandler(ApiErroException.class)
	public ResponseEntity<MensagemPadronizada> handleApiErroException(ApiErroException apiErroException) {
		Collection<String> mensagens = new ArrayList<>();
		mensagens.add(apiErroException.getReason());

		MensagemPadronizada erroPadronizado = new MensagemPadronizada(mensagens);
		return ResponseEntity.status(apiErroException.getHttpStatus()).body(erroPadronizado);
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<MensagemPadronizada> handleFeignException(FeignException feignException) {
		Collection<String> mensagens = new ArrayList<>();
		mensagens.add(feignException.getMessage());

		MensagemPadronizada erroPadronizado = new MensagemPadronizada(mensagens);
		return ResponseEntity.status(feignException.status()).body(erroPadronizado);
	}

}
