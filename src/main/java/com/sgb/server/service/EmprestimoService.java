package com.sgb.server.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import com.sgb.server.domain.Bibliotecario;
import com.sgb.server.domain.Emprestimo;
import com.sgb.server.domain.Matricula;
import com.sgb.server.domain.Multa;
import com.sgb.server.domain.Patrimonio;
import com.sgb.server.domain.PrazoEmprestimo;
import com.sgb.server.domain.Usuario;
import com.sgb.server.domain.enums.EnumRoles;
import com.sgb.server.domain.enums.EnumStatus;
import com.sgb.server.domain.enums.EnumStatusPagamento;
import com.sgb.server.domain.enums.EnumTipoMulta;
import com.sgb.server.repository.EmprestimoRepository;
import com.sgb.server.security.UserSS;
import com.sgb.server.sevice.exception.EmprestimoException;
import com.sgb.server.sevice.exception.ObjectNotFoundException;

//faz o registro de um componete do spring
@Service
public class EmprestimoService {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PrazoEmprestimoService prazoEmprestimoService;
// autowired cria um objeto quando a classe e chamada
	@Autowired
	private EmprestimoRepository repository;
	@Autowired
	private PatrimonioService patrimonioService;
	@Autowired
	private BibliotecarioService bibliotecarioService;
	@Autowired
	private MultaService multaService;
	@Autowired
	private ValorPorDiaService valorPorDiaService;

	public List<Emprestimo> findAll() {
		return repository.findAll();
	}

	public Emprestimo findById(Integer idEmprestimo) {
		// optional ter ou nao um "Emprestimo"
		Optional<Emprestimo> oEmprestimo = repository.findById(idEmprestimo);
		return oEmprestimo
				.orElseThrow(() -> new ObjectNotFoundException("Emprestimo não encontrado! id: " + idEmprestimo)); // orELSEThrow
		// retorna
	}

	public void save(Emprestimo emprestimo) {
		UserSS user = UserService.authenticated();
		if (user != null) {
			try {
				Bibliotecario bibliotecario = bibliotecarioService.findById(user.getId());
				if (bibliotecario == null || bibliotecario.getStatus() != EnumStatus.ATIVO) {
					throw new AuthorizationServiceException("Você não tem autorização para esta solicitação");
				}
				emprestimo.setBibliotecarioEmprestimo(bibliotecario);

			} catch (ObjectNotFoundException e) {
				throw new AuthorizationServiceException("Você não tem autorização para esta solicitação");
			}
		}
		Usuario usuario = usuarioService.findById(emprestimo.getUsuario().getId());
		Patrimonio patrimonio = patrimonioService.findByNumero(emprestimo.getPatrimonio().getNumero());
		emprestimo.setId(null);
		emprestimo.setAquisicao(new Date());
		emprestimo.setUsuario(usuario);
		emprestimo.setPatrimonio(patrimonio);
		emprestimo.setDevolucao(null);
		if (usuario.getRoles().contains(EnumRoles.FUNCIONARIO) && usuario.getStatus() == EnumStatus.ATIVO) {
			PrazoEmprestimo prazoEmprestimo = prazoEmprestimoService
					.findFirstByEnumRoleOrderByIdDesc(EnumRoles.FUNCIONARIO);
			if (prazoEmprestimo != null) {
				emprestimo.setPrazoEmprestimo(prazoEmprestimo);
			} else {
				throw new EmprestimoException("Nenhum prazo de emprestimo cadatrado para esse usuário!");
			}
		} else if (usuario.getRoles().contains(EnumRoles.ALUNO)) {
			Date dataAtual = new Date();
			PrazoEmprestimo prazoEmprestimo = null;
			for (Matricula matricula : usuario.getMatriculas()) {
				if (dataAtual.before(matricula.getTurma().getDtFim())
						&& dataAtual.after(matricula.getTurma().getDtInicio())) {
					prazoEmprestimo = prazoEmprestimoService.findFirstByEnumRoleOrderByIdDesc(EnumRoles.ALUNO);
					emprestimo.setPrazoEmprestimo(prazoEmprestimo);
					break;
				}
			}
			if (prazoEmprestimo == null) {
				throw new EmprestimoException("Este usuario não está apto a fazer emprestimos");
			}
		} else {
			throw new EmprestimoException("Este usuario não está apto a fazer emprestimos");
		}

		repository.save(emprestimo);

	}

	public void delete(Integer id) {
		this.findById(id);
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setId(id);
		repository.delete(emprestimo);
	}

	public Page<Emprestimo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public boolean isEmprestimoAtivo(Patrimonio patrimonio) {
		List<Emprestimo> p = repository.isEmprestimoAtivo(patrimonio.getId());
		if (p == null || p.isEmpty()) {
			return false;
		}
		return true;
	}

	public void devolucao(Emprestimo emprestimo) {
		Patrimonio patrimonio = patrimonioService.findByNumero(emprestimo.getPatrimonio().getNumero());
		List<Emprestimo> p = repository.isEmprestimoAtivo(patrimonio.getId());
		emprestimo = p.get(0);

		UserSS user = UserService.authenticated();
		if (user != null) {
			try {
				Bibliotecario bibliotecario = bibliotecarioService.findById(user.getId());
				if (bibliotecario == null || bibliotecario.getStatus() != EnumStatus.ATIVO) {
					throw new AuthorizationServiceException("Você não tem autorização para esta solicitação");
				}
				emprestimo.setBibliotecarioDevolucao(bibliotecario);

			} catch (ObjectNotFoundException e) {
				throw new AuthorizationServiceException("Você não tem autorização para esta solicitação");
			}
		}

		emprestimo.setDevolucao(new Date());

		repository.save(emprestimo);
		Calendar diaDevolucao = Calendar.getInstance();
		Calendar diaLimite = Calendar.getInstance();
		diaDevolucao.setTime(emprestimo.getDevolucao());
		diaLimite.setTime(emprestimo.getAquisicao());
		diaLimite.add(Calendar.DATE, emprestimo.getPrazoEmprestimo().getPrazo());

		if (diaDevolucao.after(diaLimite)) {
			emprestimo.getMultas().add(new Multa(null, EnumStatusPagamento.PEDENTE, emprestimo, null,
					EnumTipoMulta.ATRASO, valorPorDiaService.getValorAtual()));
		}
		if (emprestimo.getMultas() != null && !emprestimo.getMultas().isEmpty()) {
			multaService.save(emprestimo.getMultas());
		}

	}

	public Integer countEmprestimosAtivos(Integer usuario) {
		Usuario u;
		try {
			u = usuarioService.findById(usuario);

		} catch (ObjectNotFoundException e) {
			return null;
		}
		return repository.countByUsuarioAndDevolucaoIsNull(u);
	}

}
