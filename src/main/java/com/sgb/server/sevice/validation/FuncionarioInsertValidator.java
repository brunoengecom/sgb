package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.dto.FuncionarioNewDTO;
import com.sgb.server.domain.enums.EnumRoles;
import com.sgb.server.service.UsuarioService;
import com.sgb.server.sevice.exception.FieldMessage;

public class FuncionarioInsertValidator implements ConstraintValidator<FuncionarioInsert, FuncionarioNewDTO> {
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public boolean isValid(FuncionarioNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Usuario usuario = new Usuario();
		Date dataAtual = new Date();

		usuario = usuarioService.findByCpf(value.getCpf());
		if (usuario != null && usuario.getRoles().contains(EnumRoles.FUNCIONARIO)) {
			list.add(new FieldMessage("CPF", "Este CPF já está cadastrado como Funcionário!"));
		}

		usuario = usuarioService.findByEmail(value.getEmail());
		if (usuario != null && usuario.getRoles().contains(EnumRoles.FUNCIONARIO)) {
			list.add(new FieldMessage("Email", "Este Email já está cadastro!"));
		}

		if (dataAtual.before(value.getDataNasc())) {
			list.add(new FieldMessage("DataNasc", "Data de Nascimento tem que ser Menor que a Data Atual!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}

//	public boolean isValid(AlunoNewDTO value, ConstraintValidatorContext context) {
//		List<FieldMessage> list = new ArrayList<>();
//		Usuario usuario = new Usuario();
//		Turma turma = new Turma();
//		Matricula matricula = new Matricula();
//		

//		

//			//????? Tenha que saber se a turma NÂO foi cadastrada
//		
//		try {
//			turma = turmaService.findById(value.getTurma());
//			
//		} catch (ObjectNotFoundException e) {
//			list.add(new FieldMessage("turma", "Esta Turma não está cadastrada!"));
//		}
//		
//		
//		//????
//		//-->FUNCIONARIOInsert
//		
//		

//	}
