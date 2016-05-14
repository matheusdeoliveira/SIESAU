package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the secretaria_saude database table.
 * 
 */
@Entity
@Table(name = "secretaria_saude")
@NamedQuery(name = "SecretariaSaude.findAll", query = "SELECT s FROM SecretariaSaude s")
public class SecretariaSaude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SECRETARIA_SAUDE_CDSECSAUDE_GENERATOR", sequenceName = "SEQUENCIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECRETARIA_SAUDE_CDSECSAUDE_GENERATOR")
	@Column(name = "cd_secsaude", unique = true, nullable = false)
	private Integer cdSecsaude;

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

	@Column(name = "razao_social")
	private String razaoSocial;

	private String telefone;

	@Column(length = 2)
	private String uf;

	// bi-directional many-to-one association to Funcionario
	@OneToMany(mappedBy = "secretariaSaude")
	private List<Funcionario> funcionarios;

	// bi-directional many-to-one association to UnidadeSaude
	@OneToMany(mappedBy = "secretariaSaude")
	private List<UnidadeSaude> unidadeSaudes;

	public SecretariaSaude() {
	}	

	public SecretariaSaude(Integer cdSecsaude, String bairro, Integer cep, String cidade, String cnpj,
			String complemento, String email, String endereco, String fantasia, String numero, String razaoSocial,
			String telefone, String uf) {
		super();
		this.cdSecsaude = cdSecsaude;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.cnpj = cnpj;
		this.complemento = complemento;
		this.email = email;
		this.endereco = endereco;
		this.fantasia = fantasia;
		this.numero = numero;
		this.razaoSocial = razaoSocial;
		this.telefone = telefone;
		this.uf = uf;
	}



	public SecretariaSaude(Integer cdSecsaude, String bairro, Integer cep, String cidade, String cnpj,
			String complemento, String email, String endereco, String fantasia, String numero, String razaoSocial,
			String telefone, String uf, List<Funcionario> funcionarios, List<UnidadeSaude> unidadeSaudes) {
		super();
		this.cdSecsaude = cdSecsaude;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.cnpj = cnpj;
		this.complemento = complemento;
		this.email = email;
		this.endereco = endereco;
		this.fantasia = fantasia;
		this.numero = numero;
		this.razaoSocial = razaoSocial;
		this.telefone = telefone;
		this.uf = uf;
		this.funcionarios = funcionarios;
		this.unidadeSaudes = unidadeSaudes;
	}

	@Override
	public String toString() {
		return "SecretariaSaude [cdSecsaude=" + cdSecsaude + ", bairro=" + bairro + ", cep=" + cep + ", cidade="
				+ cidade + ", cnpj=" + cnpj + ", complemento=" + complemento + ", email=" + email + ", endereco="
				+ endereco + ", fantasia=" + fantasia + ", numero=" + numero + ", razaoSocial=" + razaoSocial
				+ ", telefone=" + telefone + ", uf=" + uf + "]";
	}

	public Integer getCdSecsaude() {
		return this.cdSecsaude;
	}

	public void setCdSecsaude(Integer cdSecsaude) {
		this.cdSecsaude = cdSecsaude;
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

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario addFuncionario(Funcionario funcionario) {
		getFuncionarios().add(funcionario);
		funcionario.setSecretariaSaude(this);

		return funcionario;
	}

	public Funcionario removeFuncionario(Funcionario funcionario) {
		getFuncionarios().remove(funcionario);
		funcionario.setSecretariaSaude(null);

		return funcionario;
	}

	public List<UnidadeSaude> getUnidadeSaudes() {
		return this.unidadeSaudes;
	}

	public void setUnidadeSaudes(List<UnidadeSaude> unidadeSaudes) {
		this.unidadeSaudes = unidadeSaudes;
	}

	public UnidadeSaude addUnidadeSaude(UnidadeSaude unidadeSaude) {
		getUnidadeSaudes().add(unidadeSaude);
		unidadeSaude.setSecretariaSaude(this);

		return unidadeSaude;
	}

	public UnidadeSaude removeUnidadeSaude(UnidadeSaude unidadeSaude) {
		getUnidadeSaudes().remove(unidadeSaude);
		unidadeSaude.setSecretariaSaude(null);

		return unidadeSaude;
	}

}