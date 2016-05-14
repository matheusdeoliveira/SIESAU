package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the atend_exame database table.
 * 
 */
@Entity
@Table(name="atend_exame")
@NamedQuery(name="AtendExame.findAll", query="SELECT a FROM AtendExame a")
public class AtendExame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATEND_EXAME_ID_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATEND_EXAME_ID_GENERATOR")
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

	//bi-directional many-to-one association to Exame
	@ManyToOne
	@JoinColumn(name="cd_exame", insertable=false, updatable=false)
	private Exame exame1;

	//bi-directional many-to-one association to Exame
	@ManyToOne
	@JoinColumn(name="cd_exame")
	private Exame exame2;

	public AtendExame() {
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

	public Exame getExame1() {
		return this.exame1;
	}

	public void setExame1(Exame exame1) {
		this.exame1 = exame1;
	}

	public Exame getExame2() {
		return this.exame2;
	}

	public void setExame2(Exame exame2) {
		this.exame2 = exame2;
	}

}