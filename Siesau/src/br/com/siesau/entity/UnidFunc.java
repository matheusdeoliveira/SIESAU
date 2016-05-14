package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the unid_func database table.
 * 
 */
@Entity
@Table(name="unid_func")
@NamedQuery(name="UnidFunc.findAll", query="SELECT u FROM UnidFunc u")
public class UnidFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UNID_FUNC_ID_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNID_FUNC_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cd_func", insertable=false, updatable=false)
	private Funcionario funcionario1;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="cd_func")
	private Funcionario funcionario2;

	//bi-directional many-to-one association to UnidadeSaude
	@ManyToOne
	@JoinColumn(name="cd_unidsaude", insertable=false, updatable=false)
	private UnidadeSaude unidadeSaude1;

	//bi-directional many-to-one association to UnidadeSaude
	@ManyToOne
	@JoinColumn(name="cd_unidsaude")
	private UnidadeSaude unidadeSaude2;

	public UnidFunc() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public UnidadeSaude getUnidadeSaude1() {
		return this.unidadeSaude1;
	}

	public void setUnidadeSaude1(UnidadeSaude unidadeSaude1) {
		this.unidadeSaude1 = unidadeSaude1;
	}

	public UnidadeSaude getUnidadeSaude2() {
		return this.unidadeSaude2;
	}

	public void setUnidadeSaude2(UnidadeSaude unidadeSaude2) {
		this.unidadeSaude2 = unidadeSaude2;
	}

}