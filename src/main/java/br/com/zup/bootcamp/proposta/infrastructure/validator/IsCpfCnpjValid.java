package br.com.zup.bootcamp.proposta.infrastructure.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { CpfCnpjValidator.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsCpfCnpjValid {

	String message() default "O documento informado não é um CPF/CNPJ válido!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
