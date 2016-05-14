package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especialidade database table.
 * 
 */
@Entity
@Table(name="especialidade")
@NamedQuery(name="Especialidade.findAll", query="SELECT e FROM Especialidade e")
public class Especialidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESPECIALIDADE_CDESPEC_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESPECIALIDADE_CDESPEC_GENERATOR")
	@Column(name="cd_espec", unique=true, nullable=false)
	private Integer cdEspec;

	@Column
	private String especialidade;

	//bi-directional many-to-one association to FuncEspec
	@OneToMany(mappedBy="especialidade1")
	private List<FuncEspec> funcEspecs1;

	//bi-directional many-to-one association to FuncEspec
	@OneToMany(mappedBy="especialidade2")
	private List<FuncEspec> funcEspecs2;

	//bi-directional many-to-many association to Funcionario
	@ManyToMany(mappedBy="especialidades")
	private List<Funcionario> funcionarios;

	//bi-directional many-to-one association to UnidEspec
	@OneToMany(mappedBy="especialidade1")
	private List<UnidEspec> unidEspecs1;

	//bi-directional many-to-one association to UnidEspec
	@OneToMany(mappedBy="especialidade2")
	private List<UnidEspec> unidEspecs2;

	//bi-directional many-to-many association to UnidadeSaude
	@ManyToMany(mappedBy="especialidades")
	private List<UnidadeSaude> unidadeSaudes;

	public Especialidade() {
	}

	public Integer getCdEspec() {
		return this.cdEspec;
	}

	public void setCdEspec(Integer cdEspec) {
		this.cdEspec = cdEspec;
	}

	public String getEspecialidade() {
		return this.especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public List<FuncEspec> getFuncEspecs1() {
		return this.funcEspecs1;
	}

	public void setFuncEspecs1(List<FuncEspec> funcEspecs1) {
		this.funcEspecs1 = funcEspecs1;
	}

	public FuncEspec addFuncEspecs1(FuncEspec funcEspecs1) {
		getFuncEspecs1().add(funcEspecs1);
		funcEspecs1.setEspecialidade1(this);

		return funcEspecs1;
	}

	public FuncEspec removeFuncEspecs1(FuncEspec funcEspecs1) {
		getFuncEspecs1().remove(funcEspecs1);
		funcEspecs1.setEspecialidade1(null);

		return funcEspecs1;
	}

	public List<FuncEspec> getFuncEspecs2() {
		return this.funcEspecs2;
	}

	public void setFuncEspecs2(List<FuncEspec> funcEspecs2) {
		this.funcEspecs2 = funcEspecs2;
	}

	public FuncEspec addFuncEspecs2(FuncEspec funcEspecs2) {
		getFuncEspecs2().add(funcEspecs2);
		funcEspecs2.setEspecialidade2(this);

		return funcEspecs2;
	}

	public FuncEspec removeFuncEspecs2(FuncEspec funcEspecs2) {
		getFuncEspecs2().remove(funcEspecs2);
		funcEspecs2.setEspecialidade2(null);

		return funcEspecs2;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<UnidEspec> getUnidEspecs1() {
		return this.unidEspecs1;
	}

	public void setUnidEspecs1(List<UnidEspec> unidEspecs1) {
		this.unidEspecs1 = unidEspecs1;
	}

	public UnidEspec addUnidEspecs1(UnidEspec unidEspecs1) {
		getUnidEspecs1().add(unidEspecs1);
		unidEspecs1.setEspecialidade1(this);

		return unidEspecs1;
	}

	public UnidEspec removeUnidEspecs1(UnidEspec unidEspecs1) {
		getUnidEspecs1().remove(unidEspecs1);
		unidEspecs1.setEspecialidade1(null);

		return unidEspecs1;
	}

	public List<UnidEspec> getUnidEspecs2() {
		return this.unidEspecs2;
	}

	public void setUnidEspecs2(List<UnidEspec> unidEspecs2) {
		this.unidEspecs2 = unidEspecs2;
	}

	public UnidEspec addUnidEspecs2(UnidEspec unidEspecs2) {
		getUnidEspecs2().add(unidEspecs2);
		unidEspecs2.setEspecialidade2(this);

		return unidEspecs2;
	}

	public UnidEspec removeUnidEspecs2(UnidEspec unidEspecs2) {
		getUnidEspecs2().remove(unidEspecs2);
		unidEspecs2.setEspecialidade2(null);

		return unidEspecs2;
	}

	public List<UnidadeSaude> getUnidadeSaudes() {
		return this.unidadeSaudes;
	}

	public void setUnidadeSaudes(List<UnidadeSaude> unidadeSaudes) {
		this.unidadeSaudes = unidadeSaudes;
	}

}