package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sgb.server.domain.dto.LivroNewDTO;
import com.sgb.server.sevice.exception.FieldMessage;



public class LivroInsertValidator implements ConstraintValidator<LivroInsert, LivroNewDTO> {
	
	@Override
	public boolean isValid(LivroNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		return list.isEmpty();
	}
	
}
