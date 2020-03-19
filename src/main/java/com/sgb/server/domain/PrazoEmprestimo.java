wpackage com.sgb.server.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgb.server.domain.enums.EnumRoles;

@Entity
public class PrazoEmprestimo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer prazo;
	private EnumRoles enumRole;
	
	@JsonIgnore
	@OneToMany(mappedBy = "prazoEmprestimo")
	private Set<Emprestimo> emprestimos = new HashSet<>();
	
	public EnumRoles getEnumRole() {
		return enumRole;
	}
	public void setEnumRole(EnumRoles enumRole) {
		this.enumRole = enumRole;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrazo() {
		return prazo;
	}
	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	public Set<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	public void setEmprestimos(Set<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
	
}
