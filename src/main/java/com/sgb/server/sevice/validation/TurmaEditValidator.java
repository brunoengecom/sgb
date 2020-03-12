package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgb.server.domain.dto.TurmaEditDTO;
import com.sgb.server.service.TurmaService;
import com.sgb.server.sevice.exception.FieldMessage;



public class TurmaEditValidator implements ConstraintValidator<TurmaEdit, TurmaEditDTO> {
	@Autowired
	TurmaService service = new TurmaService();
	
	@Override
	public boolean isValid(TurmaEditDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(value.getDtFim()!=null && value.getDtInicio()!=null) {
			if(value.getDtFim().before(value.getDtInicio())) {
				list.add(new FieldMessage("dtInicio","Data de inicio não pode ser maior que a data de fim"));
			}
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}
