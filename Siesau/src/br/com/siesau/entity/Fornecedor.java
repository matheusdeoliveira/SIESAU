package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Entity
@Table(name="fornecedor")
@NamedQuery(name="Fornecedor.findAll", query="SELECT f FROM Fornecedor f")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FORNECEDOR_CDFORN_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FORNECEDOR_CDFORN_GENERATOR")
	@Column(name="cd_forn", unique=true, nullable=false)
	private Integer cdForn;

	private Boolean ativo;

	@Column
	private String bairro;

	private Integer cep;

	@Column
	private String cidade;

	@Column(unique = true, nullable = false)
	private String cnpj;

	@Column
	private String complemento;

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

	public Fornecedor() {
	}

	
	
	public Fornecedor(Integer cdForn, Boolean ativo, String bairro, Integer cep, String cidade, String cnpj,
			String complemento, String email, String endereco, String fantasia, String numero, String razSocial,
			String tel, String uf) {
		super();
		this.cdForn = cdForn;
		this.ativo = ativo;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.cnpj = cnpj;
		this.complemento = complemento;
		this.email = email;
		this.endereco = endereco;
		this.fantasia = fantasia;
		this.numero = numero;
		this.razSocial = razSocial;
		this.tel = tel;
		this.uf = uf;
	}



	@Override
	public String toString() {
		return "Fornecedor [cdForn=" + cdForn + ", ativo=" + ativo + ", bairro=" + bairro + ", cep=" + cep + ", cidade="
				+ cidade + ", cnpj=" + cnpj + ", complemento=" + complemento + ", email=" + email + ", endereco="
				+ endereco + ", fantasia=" + fantasia + ", numero=" + numero + ", razSocial=" + razSocial + ", tel="
				+ tel + ", uf=" + uf + "]";
	}



	public Integer getCdForn() {
		return this.cdForn;
	}

	public void setCdForn(Integer cdForn) {
		this.cdForn = cdForn;
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
		return this.cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}