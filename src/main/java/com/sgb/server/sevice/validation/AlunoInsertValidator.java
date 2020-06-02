package com.sgb.server.sevice.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgb.server.domain.Matricula;
import com.sgb.server.domain.Turma;
import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.dto.AlunoNewDTO;
import com.sgb.server.domain.enums.EnumRoles;
import com.sgb.server.service.MatriculaService;
import com.sgb.server.service.TurmaService;
import com.sgb.server.service.UsuarioService;
import com.sgb.server.sevice.exception.FieldMessage;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

public class AlunoInsertValidator implements ConstraintValidator<AlunoInsert, AlunoNewDTO> {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private MatriculaService matriculaService;

	@Override
	public boolean isValid(AlunoNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Usuario usuario = new Usuario();
		Turma turma = new Turma();
		Matricula matricula = new Matricula();

		usuario = usuarioService.findByCpf(value.getCpf());
		if (usuario != null && usuario.getRoles().contains(EnumRoles.ALUNO)) {
			list.add(new FieldMessage("cpf", "Este CPF já está cadastrado como Aluno!"));
		}

		usuario = usuarioService.findByEmail(value.getEmail());
		if (usuario != null && usuario.getRoles().contains(EnumRoles.ALUNO)) {
			list.add(new FieldMessage("email", "Este Email já está cadastro!"));
		}

		try {
			turma = turmaService.findById(value.getTurma());

		} catch (ObjectNotFoundException e) {
			list.add(new FieldMessage("turma", "Esta Turma não está cadastrada!"));
		}

		matricula = matriculaService.findByNumero(value.getMatricula());
		if (usuario != null && usuario.getRoles().contains(EnumRoles.ALUNO)) {
			list.add(new FieldMessage("matricula", "Esta Matricula já está cadastrada!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
