package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the unidade_saude database table.
 * 
 */
@Entity
@Table(name = "unidade_saude")
@NamedQuery(name = "UnidadeSaude.findAll", query = "SELECT u FROM UnidadeSaude u")
public class UnidadeSaude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "UNIDADE_SAUDE_CDUNIDSAUDE_GENERATOR", sequenceName = "SEQUENCIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNIDADE_SAUDE_CDUNIDSAUDE_GENERATOR")
	@Column(name = "cd_unidsaude", unique = true, nullable = false)
	private Integer cdUnidsaude;

	private Boolean ativo;

	@Column
	private String bairro;

	private Integer cep;

	@Column
	private String cidade;

	@Column
	private String cnes;

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
	private String latitude;

	@Column
	private String longitude;

	@Column
	private String numero;

	@Column(name = "razao_social")
	private String razaoSocial;

	private String tel;

	@Column(length = 2)
	private String uf;

	// bi-directional many-to-one association to PacUnidsaude
	@OneToMany(mappedBy = "unidadeSaude1")
	private List<PacUnidsaude> pacUnidsaudes1;

	// bi-directional many-to-one association to PacUnidsaude
	@OneToMany(mappedBy = "unidadeSaude2")
	private List<PacUnidsaude> pacUnidsaudes2;

	// bi-directional many-to-one association to UnidEspec
	@OneToMany(mappedBy = "unidadeSaude1")
	private List<UnidEspec> unidEspecs1;

	// bi-directional many-to-one association to UnidEspec
	@OneToMany(mappedBy = "unidadeSaude2")
	private List<UnidEspec> unidEspecs2;

	// bi-directional many-to-one association to UnidFunc
	@OneToMany(mappedBy = "unidadeSaude1")
	private List<UnidFunc> unidFuncs1;

	// bi-directional many-to-one association to UnidFunc
	@OneToMany(mappedBy = "unidadeSaude2")
	private List<UnidFunc> unidFuncs2;

	// bi-directional many-to-many association to Especialidade
	@ManyToMany
	@JoinTable(name = "unid_espec", joinColumns = { @JoinColumn(name = "cd_unidsaude") }, inverseJoinColumns = {
			@JoinColumn(name = "cd_espec") })
	private List<Especialidade> especialidades;

	// bi-directional many-to-many association to Funcionario
	@ManyToMany
	@JoinTable(name = "unid_func", joinColumns = { @JoinColumn(name = "cd_unidsaude") }, inverseJoinColumns = {
			@JoinColumn(name = "cd_func") })
	private List<Funcionario> funcionarios;

	// bi-directional many-to-many association to Paciente
	@ManyToMany
	@JoinTable(name = "pac_unidsaude", joinColumns = { @JoinColumn(name = "cd_unidsaude") }, inverseJoinColumns = {
			@JoinColumn(name = "cd_paciente") })
	private List<Paciente> pacientes;

	// bi-directional many-to-one association to SecretariaSaude
	@ManyToOne
	@JoinColumn(name = "cd_secsaude")
	private SecretariaSaude secretariaSaude;

	public UnidadeSaude() {
	}

	public UnidadeSaude(Integer cdUnidsaude, Boolean ativo, String bairro, Integer cep, String cidade, String cnes,
			String cnpj, String complemento, String email, String endereco, String fantasia, String latitude,
			String longitude, String numero, String razaoSocial, String tel, String uf) {
		super();
		this.cdUnidsaude = cdUnidsaude;
		this.ativo = ativo;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.cnes = cnes;
		this.cnpj = cnpj;
		this.complemento = complemento;
		this.email = email;
		this.endereco = endereco;
		this.fantasia = fantasia;
		this.latitude = latitude;
		this.longitude = longitude;
		this.numero = numero;
		this.razaoSocial = razaoSocial;
		this.tel = tel;
		this.uf = uf;
	}

	public UnidadeSaude(Integer cdUnidsaude, Boolean ativo, String bairro, Integer cep, String cidade, String cnes,
			String cnpj, String complemento, String email, String endereco, String fantasia, String latitude,
			String longitude, String numero, String razaoSocial, String tel, String uf,
			List<PacUnidsaude> pacUnidsaudes1, List<PacUnidsaude> pacUnidsaudes2, List<UnidEspec> unidEspecs1,
			List<UnidEspec> unidEspecs2, List<UnidFunc> unidFuncs1, List<UnidFunc> unidFuncs2,
			List<Especialidade> especialidades, List<Funcionario> funcionarios, List<Paciente> pacientes,
			SecretariaSaude secretariaSaude) {
		super();
		this.cdUnidsaude = cdUnidsaude;
		this.ativo = ativo;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.cnes = cnes;
		this.cnpj = cnpj;
		this.complemento = complemento;
		this.email = email;
		this.endereco = endereco;
		this.fantasia = fantasia;
		this.latitude = latitude;
		this.longitude = longitude;
		this.numero = numero;
		this.razaoSocial = razaoSocial;
		this.tel = tel;
		this.uf = uf;
		this.pacUnidsaudes1 = pacUnidsaudes1;
		this.pacUnidsaudes2 = pacUnidsaudes2;
		this.unidEspecs1 = unidEspecs1;
		this.unidEspecs2 = unidEspecs2;
		this.unidFuncs1 = unidFuncs1;
		this.unidFuncs2 = unidFuncs2;
		this.especialidades = especialidades;
		this.funcionarios = funcionarios;
		this.pacientes = pacientes;
		this.secretariaSaude = secretariaSaude;
	}

	@Override
	public String toString() {
		return "UnidadeSaude [cdUnidsaude=" + cdUnidsaude + ", ativo=" + ativo + ", bairro=" + bairro + ", cep=" + cep
				+ ", cidade=" + cidade + ", cnes=" + cnes + ", cnpj=" + cnpj + ", complemento=" + complemento
				+ ", email=" + email + ", endereco=" + endereco + ", fantasia=" + fantasia + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", numero=" + numero + ", razaoSocial=" + razaoSocial + ", tel=" + tel
				+ ", uf=" + uf + "]";
	}

	public Integer getCdUnidsaude() {
		return this.cdUnidsaude;
	}

	public void setCdUnidsaude(Integer cdUnidsaude) {
		this.cdUnidsaude = cdUnidsaude;
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

	public String getCnes() {
		return this.cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
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

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<PacUnidsaude> getPacUnidsaudes1() {
		return this.pacUnidsaudes1;
	}

	public void setPacUnidsaudes1(List<PacUnidsaude> pacUnidsaudes1) {
		this.pacUnidsaudes1 = pacUnidsaudes1;
	}

	public PacUnidsaude addPacUnidsaudes1(PacUnidsaude pacUnidsaudes1) {
		getPacUnidsaudes1().add(pacUnidsaudes1);
		pacUnidsaudes1.setUnidadeSaude1(this);

		return pacUnidsaudes1;
	}

	public PacUnidsaude removePacUnidsaudes1(PacUnidsaude pacUnidsaudes1) {
		getPacUnidsaudes1().remove(pacUnidsaudes1);
		pacUnidsaudes1.setUnidadeSaude1(null);

		return pacUnidsaudes1;
	}

	public List<PacUnidsaude> getPacUnidsaudes2() {
		return this.pacUnidsaudes2;
	}

	public void setPacUnidsaudes2(List<PacUnidsaude> pacUnidsaudes2) {
		this.pacUnidsaudes2 = pacUnidsaudes2;
	}

	public PacUnidsaude addPacUnidsaudes2(PacUnidsaude pacUnidsaudes2) {
		getPacUnidsaudes2().add(pacUnidsaudes2);
		pacUnidsaudes2.setUnidadeSaude2(this);

		return pacUnidsaudes2;
	}

	public PacUnidsaude removePacUnidsaudes2(PacUnidsaude pacUnidsaudes2) {
		getPacUnidsaudes2().remove(pacUnidsaudes2);
		pacUnidsaudes2.setUnidadeSaude2(null);

		return pacUnidsaudes2;
	}

	public List<UnidEspec> getUnidEspecs1() {
		return this.unidEspecs1;
	}

	public void setUnidEspecs1(List<UnidEspec> unidEspecs1) {
		this.unidEspecs1 = unidEspecs1;
	}

	public UnidEspec addUnidEspecs1(UnidEspec unidEspecs1) {
		getUnidEspecs1().add(unidEspecs1);
		unidEspecs1.setUnidadeSaude1(this);

		return unidEspecs1;
	}

	public UnidEspec removeUnidEspecs1(UnidEspec unidEspecs1) {
		getUnidEspecs1().remove(unidEspecs1);
		unidEspecs1.setUnidadeSaude1(null);

		return unidEspecs1;
	}

	public List<UnidEspec> getUnidEspecs2() {
		return this.unidEspecs2;
	}

	public void setUnidEspecs2(List<UnidEspec> unidEspecs2) {
		this.unidEspecs2 = unidEspecs2;
	}

	public UnidEspec addUnidEspecs2(UnidEspec unidEspecs2) {
		getUnidEspecs2().add(unidEspecs2);
		unidEspecs2.setUnidadeSaude2(this);

		return unidEspecs2;
	}

	public UnidEspec removeUnidEspecs2(UnidEspec unidEspecs2) {
		getUnidEspecs2().remove(unidEspecs2);
		unidEspecs2.setUnidadeSaude2(null);

		return unidEspecs2;
	}

	public List<UnidFunc> getUnidFuncs1() {
		return this.unidFuncs1;
	}

	public void setUnidFuncs1(List<UnidFunc> unidFuncs1) {
		this.unidFuncs1 = unidFuncs1;
	}

	public UnidFunc addUnidFuncs1(UnidFunc unidFuncs1) {
		getUnidFuncs1().add(unidFuncs1);
		unidFuncs1.setUnidadeSaude1(this);

		return unidFuncs1;
	}

	public UnidFunc removeUnidFuncs1(UnidFunc unidFuncs1) {
		getUnidFuncs1().remove(unidFuncs1);
		unidFuncs1.setUnidadeSaude1(null);

		return unidFuncs1;
	}

	public List<UnidFunc> getUnidFuncs2() {
		return this.unidFuncs2;
	}

	public void setUnidFuncs2(List<UnidFunc> unidFuncs2) {
		this.unidFuncs2 = unidFuncs2;
	}

	public UnidFunc addUnidFuncs2(UnidFunc unidFuncs2) {
		getUnidFuncs2().add(unidFuncs2);
		unidFuncs2.setUnidadeSaude2(this);

		return unidFuncs2;
	}

	public UnidFunc removeUnidFuncs2(UnidFunc unidFuncs2) {
		getUnidFuncs2().remove(unidFuncs2);
		unidFuncs2.setUnidadeSaude2(null);

		return unidFuncs2;
	}

	public List<Especialidade> getEspecialidades() {
		return this.especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Paciente> getPacientes() {
		return this.pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public SecretariaSaude getSecretariaSaude() {
		return this.secretariaSaude;
	}

	public void setSecretariaSaude(SecretariaSaude secretariaSaude) {
		this.secretariaSaude = secretariaSaude;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	

}