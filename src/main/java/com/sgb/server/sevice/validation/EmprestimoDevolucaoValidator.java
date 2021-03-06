package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgb.server.domain.Patrimonio;
import com.sgb.server.dto.DevolucaoNewDTO;
import com.sgb.server.service.EmprestimoService;
import com.sgb.server.service.PatrimonioService;
import com.sgb.server.sevice.exception.FieldMessage;

public class EmprestimoDevolucaoValidator implements ConstraintValidator<EmprestimoDevolucao, DevolucaoNewDTO> {
	@Autowired
	private PatrimonioService patrimonioService;

	@Autowired
	private EmprestimoService emprestimoService;

	@Override
	public void initialize(EmprestimoDevolucao constraintAnnotation) {
	}

	@Override
	public boolean isValid(DevolucaoNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Patrimonio patrimonio = patrimonioService.findByNumero(value.getPatrimonio());

		if (patrimonio == null) {
			list.add(new FieldMessage("patrimonio", "Patrimonio não encontrado!"));
		} else {
			if (!emprestimoService.isEmprestimoAtivo(patrimonio)) {
				list.add(new FieldMessage("patrimonio", "O Patrimonio inserido não está emprestado!"));
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
