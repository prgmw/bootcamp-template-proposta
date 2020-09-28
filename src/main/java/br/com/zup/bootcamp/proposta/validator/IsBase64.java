package br.com.zup.bootcamp.proposta.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { Base64Validator.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsBase64 {

	String message() default "O valor informado para a biometria não é válido!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
