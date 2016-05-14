package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pac_unidsaude database table.
 * 
 */
@Entity
@Table(name="pac_unidsaude")
@NamedQuery(name="PacUnidsaude.findAll", query="SELECT p FROM PacUnidsaude p")
public class PacUnidsaude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAC_UNIDSAUDE_ID_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAC_UNIDSAUDE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="cd_paciente", insertable=false, updatable=false)
	private Paciente paciente1;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="cd_paciente")
	private Paciente paciente2;

	//bi-directional many-to-one association to UnidadeSaude
	@ManyToOne
	@JoinColumn(name="cd_unidsaude", insertable=false, updatable=false)
	private UnidadeSaude unidadeSaude1;

	//bi-directional many-to-one association to UnidadeSaude
	@ManyToOne
	@JoinColumn(name="cd_unidsaude")
	private UnidadeSaude unidadeSaude2;

	public PacUnidsaude() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paciente getPaciente1() {
		return this.paciente1;
	}

	public void setPaciente1(Paciente paciente1) {
		this.paciente1 = paciente1;
	}

	public Paciente getPaciente2() {
		return this.paciente2;
	}

	public void setPaciente2(Paciente paciente2) {
		this.paciente2 = paciente2;
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