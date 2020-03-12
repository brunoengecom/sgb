package com.sgb.server.sevice.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = TurmaEditValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TurmaEdit {
	String message() default "Erro de Validação";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};
			
}
