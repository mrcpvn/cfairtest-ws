package cfairtest.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class MessageValidator{

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	public static <T> void validate(T message) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(message);
		if(constraintViolations.size() != 0){
			//manage violation
		}
	}
}
