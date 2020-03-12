package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sgb.server.domain.dto.TurmaNewDTO;
import com.sgb.server.sevice.exception.FieldMessage;



public class TurmaInsertValidator implements ConstraintValidator<TurmaInsert, TurmaNewDTO> {
	
	@Override
	public boolean isValid(TurmaNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(value.getDtFim().before(value.getDtInicio())) {
			list.add(new FieldMessage("dtInicio","Data de inicio não pode ser maior que a data de fim"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}
