package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fabricante database table.
 * 
 */
@Entity
@Table(name="fabricante")
@NamedQuery(name="Fabricante.findAll", query="SELECT f FROM Fabricante f")
public class Fabricante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FABRICANTE_CDFABRIC_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FABRICANTE_CDFABRIC_GENERATOR")
	@Column(name="cd_fabric", unique=true, nullable=false)
	private Integer cdFabric;

	private Boolean ativo;

	@Column
	private String bairro;

	@Column
	private Integer cep;

	@Column
	private String cidade;

	@Column(unique = true, nullable = false)
	private Integer cnpj;

	@Column(unique = true, nullable = false)
	private String email;

	@Column
	private String endereco;

	@Column
	private String fantasia;

	@Column
	private String numero;

	@Column(name="raz_social")
	private String razSocial;

	private String tel;

	@Column(length=2)
	private String uf;

	//bi-directional many-to-one association to Medicamento
	@OneToMany(mappedBy="fabricante")
	private List<Medicamento> medicamentos;

	public Fabricante() {
	}

	public Integer getCdFabric() {
		return this.cdFabric;
	}

	public void setCdFabric(Integer cdFabric) {
		this.cdFabric = cdFabric;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFantasia() {
		return this.fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRazSocial() {
		return this.razSocial;
	}

	public void setRazSocial(String razSocial) {
		this.razSocial = razSocial;
	}

	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Medicamento> getMedicamentos() {
		return this.medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public Medicamento addMedicamento(Medicamento medicamento) {
		getMedicamentos().add(medicamento);
		medicamento.setFabricante(this);

		return medicamento;
	}

	public Medicamento removeMedicamento(Medicamento medicamento) {
		getMedicamentos().remove(medicamento);
		medicamento.setFabricante(null);

		return medicamento;
	}

}