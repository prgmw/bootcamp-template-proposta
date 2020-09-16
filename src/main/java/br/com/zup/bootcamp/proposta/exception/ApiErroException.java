package br.com.zup.bootcamp.proposta.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiErroException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5692356599457275928L;
	
	private final HttpStatus httpStatus;
	private final String reason;

}
