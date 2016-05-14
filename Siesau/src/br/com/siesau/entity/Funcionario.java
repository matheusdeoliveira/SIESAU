package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@Table(name = "funcionario")
@NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FUNCIONARIO_CDFUNC_GENERATOR", sequenceName = "SEQUENCIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONARIO_CDFUNC_GENERATOR")
	@Column(name = "cd_func", unique = true, nullable = false)
	private Integer cdFunc;

	private Boolean ativo;

	@Column
	private String bairro;

	private Integer cep;

	@Column
	private String cidade;

	@Column
	private String complemento;

	@Column(unique = true, nullable = false)
	private String cpf;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_admis")
	private Date dataAdmis;

	@Column
	private String endereco;

	@Column(name = "estado_civil")
	private String estadoCivil;

	@Column
	private String login;

	@Column
	private String nome;

	private Integer numero;

	@Column
	private String registro;

	@Column(unique = true, nullable = false)
	private String rg;

	@Column
	private String senha;

	@Column
	private String sexo;

	@Column(name = "foto")
	private String foto;

	@Column(name = "tel_cel")
	private String telCel;

	@Column(name = "tel_res")
	private String telRes;

	@Column(length = 2)
	private String uf;

	// bi-directional many-to-one association to Atendimento
	@OneToMany(mappedBy = "funcionario")
	private List<Atendimento> atendimentos;

	// bi-directional many-to-one association to FuncEspec
	@OneToMany(mappedBy = "funcionario1")
	private List<FuncEspec> funcEspecs1;

	// bi-directional many-to-one association to FuncEspec
	@OneToMany(mappedBy = "funcionario2")
	private List<FuncEspec> funcEspecs2;

	// bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name = "cd_cargo")
	private Cargo cargo;

	// bi-directional many-to-many association to Especialidade
	@ManyToMany
	@JoinTable(name = "func_espec", joinColumns = { @JoinColumn(name = "cd_func") }, inverseJoinColumns = {
			@JoinColumn(name = "cd_espec") })
	private List<Especialidade> especialidades;

	// bi-directional many-to-one association to SecretariaSaude
	@ManyToOne
	@JoinColumn(name = "cd_secsaude")
	private SecretariaSaude secretariaSaude;

	// bi-directional many-to-one association to TipoFuncionario
	@ManyToOne
	@JoinColumn(name = "cd_tipofunc")
	private TipoFuncionario tipoFuncionario;

	// bi-directional many-to-one association to UnidFunc
	@OneToMany(mappedBy = "funcionario1")
	private List<UnidFunc> unidFuncs1;

	// bi-directional many-to-one association to UnidFunc
	@OneToMany(mappedBy = "funcionario2")
	private List<UnidFunc> unidFuncs2;

	// bi-directional many-to-many association to UnidadeSaude
	@ManyToMany(mappedBy = "funcionarios")
	private List<UnidadeSaude> unidadeSaudes;

	public Funcionario() {
	}

	public Integer getCdFunc() {
		return this.cdFunc;
	}

	public void setCdFunc(Integer cdFunc) {
		this.cdFunc = cdFunc;
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

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getDataAdmis() {
		return this.dataAdmis;
	}

	public void setDataAdmis(Date dataAdmis) {
		this.dataAdmis = dataAdmis;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRegistro() {
		return this.registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelCel() {
		return telCel;
	}

	public void setTelCel(String telCel) {
		this.telCel = telCel;
	}

	public String getTelRes() {
		return telRes;
	}

	public void setTelRes(String telRes) {
		this.telRes = telRes;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Atendimento> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Atendimento addAtendimento(Atendimento atendimento) {
		getAtendimentos().add(atendimento);
		atendimento.setFuncionario(this);

		return atendimento;
	}

	public Atendimento removeAtendimento(Atendimento atendimento) {
		getAtendimentos().remove(atendimento);
		atendimento.setFuncionario(null);

		return atendimento;
	}

	public List<FuncEspec> getFuncEspecs1() {
		return this.funcEspecs1;
	}

	public void setFuncEspecs1(List<FuncEspec> funcEspecs1) {
		this.funcEspecs1 = funcEspecs1;
	}

	public FuncEspec addFuncEspecs1(FuncEspec funcEspecs1) {
		getFuncEspecs1().add(funcEspecs1);
		funcEspecs1.setFuncionario1(this);

		return funcEspecs1;
	}

	public FuncEspec removeFuncEspecs1(FuncEspec funcEspecs1) {
		getFuncEspecs1().remove(funcEspecs1);
		funcEspecs1.setFuncionario1(null);

		return funcEspecs1;
	}

	public List<FuncEspec> getFuncEspecs2() {
		return this.funcEspecs2;
	}

	public void setFuncEspecs2(List<FuncEspec> funcEspecs2) {
		this.funcEspecs2 = funcEspecs2;
	}

	public FuncEspec addFuncEspecs2(FuncEspec funcEspecs2) {
		getFuncEspecs2().add(funcEspecs2);
		funcEspecs2.setFuncionario2(this);

		return funcEspecs2;
	}

	public FuncEspec removeFuncEspecs2(FuncEspec funcEspecs2) {
		getFuncEspecs2().remove(funcEspecs2);
		funcEspecs2.setFuncionario2(null);

		return funcEspecs2;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Especialidade> getEspecialidades() {
		return this.especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public SecretariaSaude getSecretariaSaude() {
		return this.secretariaSaude;
	}

	public void setSecretariaSaude(SecretariaSaude secretariaSaude) {
		this.secretariaSaude = secretariaSaude;
	}

	public TipoFuncionario getTipoFuncionario() {
		return this.tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public List<UnidFunc> getUnidFuncs1() {
		return this.unidFuncs1;
	}

	public void setUnidFuncs1(List<UnidFunc> unidFuncs1) {
		this.unidFuncs1 = unidFuncs1;
	}

	public UnidFunc addUnidFuncs1(UnidFunc unidFuncs1) {
		getUnidFuncs1().add(unidFuncs1);
		unidFuncs1.setFuncionario1(this);

		return unidFuncs1;
	}

	public UnidFunc removeUnidFuncs1(UnidFunc unidFuncs1) {
		getUnidFuncs1().remove(unidFuncs1);
		unidFuncs1.setFuncionario1(null);

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
		unidFuncs2.setFuncionario2(this);

		return unidFuncs2;
	}

	public UnidFunc removeUnidFuncs2(UnidFunc unidFuncs2) {
		getUnidFuncs2().remove(unidFuncs2);
		unidFuncs2.setFuncionario2(null);

		return unidFuncs2;
	}

	public List<UnidadeSaude> getUnidadeSaudes() {
		return this.unidadeSaudes;
	}

	public void setUnidadeSaudes(List<UnidadeSaude> unidadeSaudes) {
		this.unidadeSaudes = unidadeSaudes;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}