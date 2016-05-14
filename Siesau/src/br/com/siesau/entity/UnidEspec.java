package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the unid_espec database table.
 * 
 */
@Entity
@Table(name="unid_espec")
@NamedQuery(name="UnidEspec.findAll", query="SELECT u FROM UnidEspec u")
public class UnidEspec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UNID_ESPEC_ID_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNID_ESPEC_ID_GENERATOR")
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

	//bi-directional many-to-one association to UnidadeSaude
	@ManyToOne
	@JoinColumn(name="cd_unidsaude", insertable=false, updatable=false)
	private UnidadeSaude unidadeSaude1;

	//bi-directional many-to-one association to UnidadeSaude
	@ManyToOne
	@JoinColumn(name="cd_unidsaude")
	private UnidadeSaude unidadeSaude2;

	public UnidEspec() {
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