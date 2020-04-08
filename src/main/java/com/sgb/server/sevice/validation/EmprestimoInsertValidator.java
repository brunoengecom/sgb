package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgb.server.domain.Patrimonio;
import com.sgb.server.domain.dto.EmprestimoNewDTO;
import com.sgb.server.service.EmprestimoService;
import com.sgb.server.service.PatrimonioService;
import com.sgb.server.sevice.exception.FieldMessage;

public class EmprestimoInsertValidator implements ConstraintValidator<EmprestimoInsert, EmprestimoNewDTO> {
	@Autowired
	private PatrimonioService patrimonioService;

	@Autowired
	private EmprestimoService emprestimoService;

	@Override
	public void initialize(EmprestimoInsert constraintAnnotation) {
	}

	@Override
	public boolean isValid(EmprestimoNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Patrimonio patrimonio = patrimonioService.findByNumero(value.getPatrimonio());
		Integer valor = emprestimoService.countEmprestimosAtivos(value.getUsuario());

		if (valor == null) {
			list.add(new FieldMessage("usuario", "Usuario não encontrado"));
		} else if (valor >= 3) {
			list.add(new FieldMessage("usuario", "Limite de emprestimos excedido pelo usuario"));
		}

		if (patrimonio == null) {
			list.add(new FieldMessage("patrimonio", "Patrimonio não encontrado!"));
		} else {
			if (emprestimoService.isEmprestimoAtivo(patrimonio)) {
				list.add(new FieldMessage("patrimonio", "O Patrimonio inserido já está emprestado!"));
			}
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}

}
