package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the atend_doenca database table.
 * 
 */
@Entity
@Table(name="atend_doenca")
@NamedQuery(name="AtendDoenca.findAll", query="SELECT a FROM AtendDoenca a")
public class AtendDoenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATEND_DOENCA_ID_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATEND_DOENCA_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Atendimento
	@ManyToOne
	@JoinColumn(name="cd_atend", insertable=false, updatable=false)
	private Atendimento atendimento1;

	//bi-directional many-to-one association to Atendimento
	@ManyToOne
	@JoinColumn(name="cd_atend")
	private Atendimento atendimento2;

	//bi-directional many-to-one association to Doenca
	@ManyToOne
	@JoinColumn(name="cd_doenca", insertable=false, updatable=false)
	private Doenca doenca1;

	//bi-directional many-to-one association to Doenca
	@ManyToOne
	@JoinColumn(name="cd_doenca")
	private Doenca doenca2;

	public AtendDoenca() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Atendimento getAtendimento1() {
		return this.atendimento1;
	}

	public void setAtendimento1(Atendimento atendimento1) {
		this.atendimento1 = atendimento1;
	}

	public Atendimento getAtendimento2() {
		return this.atendimento2;
	}

	public void setAtendimento2(Atendimento atendimento2) {
		this.atendimento2 = atendimento2;
	}

	public Doenca getDoenca1() {
		return this.doenca1;
	}

	public void setDoenca1(Doenca doenca1) {
		this.doenca1 = doenca1;
	}

	public Doenca getDoenca2() {
		return this.doenca2;
	}

	public void setDoenca2(Doenca doenca2) {
		this.doenca2 = doenca2;
	}

}