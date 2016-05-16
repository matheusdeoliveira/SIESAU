package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cargo database table.
 * 
 */
@Entity
@Table(name="cargo")
@NamedQuery(name="Cargo.findAll", query="SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CARGO_CDCARGO_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARGO_CDCARGO_GENERATOR")
	@Column(name="cd_cargo", unique=true, nullable=false)
	private Integer cdCargo;

	@Column
	private String cargo;

	@Column
	private String conselho;

	//bi-directional many-to-one association to Funcionario
	@OneToMany(mappedBy="cargo")
	private List<Funcionario> funcionarios;

	public Cargo() {
	}

	public Integer getCdCargo() {
		return this.cdCargo;
	}

	public void setCdCargo(Integer cdCargo) {
		this.cdCargo = cdCargo;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getConselho() {
		return this.conselho;
	}

	public void setConselho(String conselho) {
		this.conselho = conselho;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario addFuncionario(Funcionario funcionario) {
		getFuncionarios().add(funcionario);
		funcionario.setCargo(this);

		return funcionario;
	}

	public Funcionario removeFuncionario(Funcionario funcionario) {
		getFuncionarios().remove(funcionario);
		funcionario.setCargo(null);

		return funcionario;
	}
	
	@Override
	public int hashCode() {
		return (cdCargo == null) ? 0 : cdCargo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Cargo) {
			return ((Cargo) obj).getCdCargo().equals(this.cdCargo);
		}
		return false;
	}


}