package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the func_espec database table.
 * 
 */
@Entity
@Table(name="func_espec")
@NamedQuery(name="FuncEspec.findAll", query="SELECT f FROM FuncEspec f")
public class FuncEspec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FUNC_ESPEC_ID_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FUNC_ESPEC_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Especialidade
	@ManyToOne
	@JoinColumn(name="cd_espec", insertable=false, updatable=false)
	private Especialidade especialidade1;

	//bi-directional many-to-one association to Especialidade
	@ManyToOne
	@JoinColumn(name="cd_espec")
	private Especialidade especialidade2;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cd_func", insertable=false, updatable=false)
	private Funcionario funcionario1;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cd_func")
	private Funcionario funcionario2;

	public FuncEspec() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Especialidade getEspecialidade1() {
		return this.especialidade1;
	}

	public void setEspecialidade1(Especialidade especialidade1) {
		this.especialidade1 = especialidade1;
	}

	public Especialidade getEspecialidade2() {
		return this.especialidade2;
	}

	public void setEspecialidade2(Especialidade especialidade2) {
		this.especialidade2 = especialidade2;
	}

	public Funcionario getFuncionario1() {
		return this.funcionario1;
	}

	public void setFuncionario1(Funcionario funcionario1) {
		this.funcionario1 = funcionario1;
	}

	public Funcionario getFuncionario2() {
		return this.funcionario2;
	}

	public void setFuncionario2(Funcionario funcionario2) {
		this.funcionario2 = funcionario2;
	}

}