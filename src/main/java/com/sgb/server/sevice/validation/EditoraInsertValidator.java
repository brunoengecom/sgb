package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgb.server.domain.Editora;
import com.sgb.server.domain.dto.EditoraNewDTO;
import com.sgb.server.repository.EditoraRepository;
import com.sgb.server.service.EditoraService;
import com.sgb.server.sevice.exception.FieldMessage;

public class EditoraInsertValidator implements ConstraintValidator<EditoraInsert, EditoraNewDTO> {
	@Autowired
	private EditoraService editoraService;
	@Autowired
	private EditoraRepository repo;

	@Override
	public boolean isValid(EditoraNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Editora editora = new Editora();

		editora = editoraService.findByNome(value.getNome());
		if (editora != null) {
			list.add(new FieldMessage("nome", "O Nome desta Editora já está sendo usado!"));
		}

		editora = editoraService.findByCnpj(value.getCnpj());
		if (editora != null) {
			list.add(new FieldMessage("cnpj", "Este CNPJ já esta cadastrado!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
