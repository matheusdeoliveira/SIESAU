package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the medicamento database table.
 * 
 */
@Entity
@Table(name="medicamento")
@NamedQuery(name="Medicamento.findAll", query="SELECT m FROM Medicamento m")
public class Medicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MEDICAMENTO_CDMEDICAM_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEDICAMENTO_CDMEDICAM_GENERATOR")
	@Column(name="cd_medicam", unique=true, nullable=false)
	private Integer cdMedicam;

	private Boolean ativo;

	@Column
	private String concentracao;

	private Boolean controlado;

	@Column
	private String forma;

	@Column
	private String ms;

	@Column(name="nome_ref")
	private String nomeRef;

	@Column
	private String subst;

	//bi-directional many-to-one association to Fabricante
	@ManyToOne
	@JoinColumn(name="cd_fabric")
	private Fabricante fabricante;

	//bi-directional many-to-one association to ReceiMedic
	@OneToMany(mappedBy="medicamento1")
	private List<ReceiMedic> receiMedics1;

	//bi-directional many-to-one association to ReceiMedic
	@OneToMany(mappedBy="medicamento2")
	private List<ReceiMedic> receiMedics2;

	//bi-directional many-to-many association to Receita
	@ManyToMany(mappedBy="medicamentos")
	private List<Receita> receitas;

	public Medicamento() {
	}

	public Integer getCdMedicam() {
		return this.cdMedicam;
	}

	public void setCdMedicam(Integer cdMedicam) {
		this.cdMedicam = cdMedicam;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getConcentracao() {
		return this.concentracao;
	}

	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}

	public Boolean getControlado() {
		return this.controlado;
	}

	public void setControlado(Boolean controlado) {
		this.controlado = controlado;
	}

	public String getForma() {
		return this.forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getNomeRef() {
		return this.nomeRef;
	}

	public void setNomeRef(String nomeRef) {
		this.nomeRef = nomeRef;
	}

	public String getSubst() {
		return this.subst;
	}

	public void setSubst(String subst) {
		this.subst = subst;
	}

	public Fabricante getFabricante() {
		return this.fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<ReceiMedic> getReceiMedics1() {
		return this.receiMedics1;
	}

	public void setReceiMedics1(List<ReceiMedic> receiMedics1) {
		this.receiMedics1 = receiMedics1;
	}

	public ReceiMedic addReceiMedics1(ReceiMedic receiMedics1) {
		getReceiMedics1().add(receiMedics1);
		receiMedics1.setMedicamento1(this);

		return receiMedics1;
	}

	public ReceiMedic removeReceiMedics1(ReceiMedic receiMedics1) {
		getReceiMedics1().remove(receiMedics1);
		receiMedics1.setMedicamento1(null);

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
		receiMedics2.setMedicamento2(this);

		return receiMedics2;
	}

	public ReceiMedic removeReceiMedics2(ReceiMedic receiMedics2) {
		getReceiMedics2().remove(receiMedics2);
		receiMedics2.setMedicamento2(null);

		return receiMedics2;
	}

	public List<Receita> getReceitas() {
		return this.receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

}