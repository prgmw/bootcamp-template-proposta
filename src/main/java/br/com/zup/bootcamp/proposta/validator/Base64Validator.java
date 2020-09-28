package br.com.zup.bootcamp.proposta.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.codec.binary.Base64;

public class Base64Validator implements ConstraintValidator<IsBase64, CharSequence> {

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if (!Optional.ofNullable(value).isPresent()) {
			return true;
		}

		return Base64.isBase64(value.toString());
	}

}
