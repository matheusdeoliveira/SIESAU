package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name="paciente")
@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PACIENTE_CDPACIENTE_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PACIENTE_CDPACIENTE_GENERATOR")
	@Column(name="cd_paciente", unique=true, nullable=false)
	private Integer cdPaciente;

	@Column
	private String bairro;

	@Column(unique = true, nullable = false)
	private String cartaoSus;

	private Integer cep;

	@Column
	private String cidade;

	@Column
	private String complemento;

	@Column(unique = true, nullable = false)
	private String cpf;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cad")
	private Date dataCad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_nasc")
	private Date dataNasc;

	@Column(unique = true)
	private String email;

	@Column
	private String endereco;

	@Column(name="est_civil")
	private String estCivil;

	@Column
	private String etnia;

	@Column
	private String latitude;
	
	@Column(name="foto")
	private String foto;

	@Column
	private String longitude;

	@Column
	private String naturalidade;

	@Column
	private String nome;

	@Column(name="nome_mae")
	private String nomeMae;

	@Column(name="nome_pai")
	private String nomePai;

	@Column
	private String numero;

	@Column
	private String obs;

	@Column
	private String profissao;

	@Column(unique = true, nullable = false)
	private String rg;

	@Column
	private String sexo;

	@Column(name="tel_cel")
	private String telCel;

	@Column(name="tel_res")
	private String telRes;

	@Column(length=2)
	private String uf;
	
	//bi-directional many-to-one association to Atendimento
	@OneToMany(mappedBy="paciente")
	private List<Atendimento> atendimentos;

	//bi-directional many-to-one association to PacUnidsaude
	@OneToMany(mappedBy="paciente1")
	private List<PacUnidsaude> pacUnidsaudes1;

	//bi-directional many-to-one association to PacUnidsaude
	@OneToMany(mappedBy="paciente2")
	private List<PacUnidsaude> pacUnidsaudes2;

	//bi-directional many-to-many association to UnidadeSaude
	@ManyToMany(mappedBy="pacientes")
	private List<UnidadeSaude> unidadeSaudes;
	
	@Column
	private String tipoAlergia;
	
	@Column
	private Boolean alergia;

	
	public Paciente() {
	}

	public Integer getCdPaciente() {
		return this.cdPaciente;
	}

	public void setCdPaciente(Integer cdPaciente) {
		this.cdPaciente = cdPaciente;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	

	public String getCartaoSus() {
		return cartaoSus;
	}

	public void setCartaoSus(String cartaoSus) {
		this.cartaoSus = cartaoSus;
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

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCad() {
		return this.dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public Date getDataNasc() {
		return this.dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
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

	public String getEstCivil() {
		return this.estCivil;
	}

	public void setEstCivil(String estCivil) {
		this.estCivil = estCivil;
	}

	public String getEtnia() {
		return this.etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
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

	public String getNaturalidade() {
		return this.naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMae() {
		return this.nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return this.nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getProfissao() {
		return this.profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
		atendimento.setPaciente(this);

		return atendimento;
	}

	public Atendimento removeAtendimento(Atendimento atendimento) {
		getAtendimentos().remove(atendimento);
		atendimento.setPaciente(null);

		return atendimento;
	}

	public List<PacUnidsaude> getPacUnidsaudes1() {
		return this.pacUnidsaudes1;
	}

	public void setPacUnidsaudes1(List<PacUnidsaude> pacUnidsaudes1) {
		this.pacUnidsaudes1 = pacUnidsaudes1;
	}

	public PacUnidsaude addPacUnidsaudes1(PacUnidsaude pacUnidsaudes1) {
		getPacUnidsaudes1().add(pacUnidsaudes1);
		pacUnidsaudes1.setPaciente1(this);

		return pacUnidsaudes1;
	}

	public PacUnidsaude removePacUnidsaudes1(PacUnidsaude pacUnidsaudes1) {
		getPacUnidsaudes1().remove(pacUnidsaudes1);
		pacUnidsaudes1.setPaciente1(null);

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
		pacUnidsaudes2.setPaciente2(this);

		return pacUnidsaudes2;
	}

	public PacUnidsaude removePacUnidsaudes2(PacUnidsaude pacUnidsaudes2) {
		getPacUnidsaudes2().remove(pacUnidsaudes2);
		pacUnidsaudes2.setPaciente2(null);

		return pacUnidsaudes2;
	}

	public List<UnidadeSaude> getUnidadeSaudes() {
		return this.unidadeSaudes;
	}

	public void setUnidadeSaudes(List<UnidadeSaude> unidadeSaudes) {
		this.unidadeSaudes = unidadeSaudes;
	}

	public String getTipoAlergia() {
		return tipoAlergia;
	}

	public void setTipoAlergia(String tipoAlergia) {
		this.tipoAlergia = tipoAlergia;
	}

	public boolean isAlergia() {
		return alergia = (alergia==null)?false:(alergia!=null)?alergia:alergia;
	}

	public void setAlergia(boolean alergia) {
		this.alergia = alergia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getAlergia() {
		return alergia;
	}

	public void setAlergia(Boolean alergia) {
		this.alergia = alergia;
	}
	
	
	
}