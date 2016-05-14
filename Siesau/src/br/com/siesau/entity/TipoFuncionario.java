package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_funcionario database table.
 * 
 */
@Entity
@Table(name="tipo_funcionario")
@NamedQuery(name="TipoFuncionario.findAll", query="SELECT t FROM TipoFuncionario t")
public class TipoFuncionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_FUNCIONARIO_CDTIPOFUNC_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_FUNCIONARIO_CDTIPOFUNC_GENERATOR")
	@Column(name="cd_tipofunc", unique=true, nullable=false)
	private Integer cdTipofunc;

	@Column
	private String nome;

	//bi-directional many-to-one association to Funcionario
	@OneToMany(mappedBy="tipoFuncionario")
	private List<Funcionario> funcionarios;

	public TipoFuncionario() {
	}

	public Integer getCdTipofunc() {
		return this.cdTipofunc;
	}

	public void setCdTipofunc(Integer cdTipofunc) {
		this.cdTipofunc = cdTipofunc;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario addFuncionario(Funcionario funcionario) {
		getFuncionarios().add(funcionario);
		funcionario.setTipoFuncionario(this);

		return funcionario;
	}

	public Funcionario removeFuncionario(Funcionario funcionario) {
		getFuncionarios().remove(funcionario);
		funcionario.setTipoFuncionario(null);

		return funcionario;
	}

}