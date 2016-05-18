package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the receita database table.
 * 
 */
@Entity
@Table(name="receita")
@NamedQuery(name="Receita.findAll", query="SELECT r FROM Receita r")
public class Receita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RECEITA_CDRECEITA_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECEITA_CDRECEITA_GENERATOR")
	@Column(name="cd_receita", unique=true, nullable=false)
	private Integer cdReceita;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column
	private String obs;

	@Column(name="posologia_recei")
	private String posologiaRecei;

	//bi-directional many-to-one association to ReceiMedic
	@OneToMany(mappedBy="receita1")
	private List<ReceiMedic> receiMedics1;

	//bi-directional many-to-one association to ReceiMedic
	@OneToMany(mappedBy="receita2")
	private List<ReceiMedic> receiMedics2;

	//bi-directional many-to-one association to Atendimento
	@ManyToOne
	@JoinColumn(name="cd_atend")
	private Atendimento atendimento;

	//bi-directional many-to-many association to Medicamento
	@ManyToMany
	@JoinTable(
		name="recei_medic"
		, joinColumns={
			@JoinColumn(name="cd_receita")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cd_medicam")
			}
		)
	private List<Medicamento> medicamentos;

	public Receita() {
	}

	public Integer getCdReceita() {
		return this.cdReceita;
	}

	public void setCdReceita(Integer cdReceita) {
		this.cdReceita = cdReceita;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getPosologiaRecei() {
		return this.posologiaRecei;
	}

	public void setPosologiaRecei(String posologiaRecei) {
		this.posologiaRecei = posologiaRecei;
	}

	public List<ReceiMedic> getReceiMedics1() {
		return this.receiMedics1;
	}

	public void setReceiMedics1(List<ReceiMedic> receiMedics1) {
		this.receiMedics1 = receiMedics1;
	}

	public ReceiMedic addReceiMedics1(ReceiMedic receiMedics1) {
		getReceiMedics1().add(receiMedics1);
		receiMedics1.setReceita1(this);

		return receiMedics1;
	}

	public ReceiMedic removeReceiMedics1(ReceiMedic receiMedics1) {
		getReceiMedics1().remove(receiMedics1);
		receiMedics1.setReceita1(null);

		return receiMedics1;
	}

	public List<ReceiMedic> getReceiMedics2() {
		return this.receiMedics2;
	}

	public void setReceiMedics2(List<ReceiMedic> receiMedics2) {
		this.receiMedics2 = receiMedics2;
	}

	public ReceiMedic addReceiMedics2(ReceiMedic receiMedics2) {
		getReceiMedics2().add(receiMedics2);
		receiMedics2.setReceita2(this);

		return receiMedics2;
	}

	public ReceiMedic removeReceiMedics2(ReceiMedic receiMedics2) {
		getReceiMedics2().remove(receiMedics2);
		receiMedics2.setReceita2(null);

		return receiMedics2;
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<Medicamento> getMedicamentos() {
		return this.medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

}