package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgb.server.domain.dto.LivroNewDTO;
import com.sgb.server.service.LivroService;
import com.sgb.server.sevice.exception.FieldMessage;



public class LivroInsertValidator implements ConstraintValidator<LivroInsert, LivroNewDTO> {
	@Autowired
	private LivroService livroService;
		
	@Override
	public boolean isValid(LivroNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(livroService.findByIsbn(value.getIsbn()) != null) {
			list.add(new FieldMessage("isbn","O ISBN já está cadastrado"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}
