package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sgb.server.domain.dto.EmprestimoNewDTO;
import com.sgb.server.sevice.exception.FieldMessage;



public class EmprestimoInsertValidator implements ConstraintValidator<EmprestimoInsert, EmprestimoNewDTO> {
	
	@Override
	public boolean isValid(EmprestimoNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		return list.isEmpty();
	}
	
}
