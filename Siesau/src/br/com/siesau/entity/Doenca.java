package br.com.siesau.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the doenca database table.
 * 
 */
@Entity
@Table(name="doenca")
@NamedQuery(name="Doenca.findAll", query="SELECT d FROM Doenca d")
public class Doenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOENCA_CDDOENCA_GENERATOR", sequenceName="SEQUENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOENCA_CDDOENCA_GENERATOR")
	@Column(name="cd_doenca", unique=true, nullable=false)
	private Integer cdDoenca;

	@Column
	private String cid;

	@Column(length=600)
	private String nome;

	//bi-directional many-to-one association to AtendDoenca
	@OneToMany(mappedBy="doenca1")
	private List<AtendDoenca> atendDoencas1;

	//bi-directional many-to-one association to AtendDoenca
	@OneToMany(mappedBy="doenca2")
	private List<AtendDoenca> atendDoencas2;

	//bi-directional many-to-many association to Atendimento
	@ManyToMany
	@JoinTable(
		name="atend_doenca"
		, joinColumns={
			@JoinColumn(name="cd_doenca")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cd_atend")
			}
		)
	private List<Atendimento> atendimentos;

	//bi-directional many-to-one association to Cid10Capitulo
	@ManyToOne
	@JoinColumn(name="cd_capitulo")
	private Cid10Capitulo cid10Capitulo;

	//bi-directional many-to-one association to Cid10Categ
	@ManyToOne
	@JoinColumn(name="cd_cid_10_categ")
	private Cid10Categ cid10Categ;

	//bi-directional many-to-one association to Cid10Grupo
	@ManyToOne
	@JoinColumn(name="cd_cid_10_grupo")
	private Cid10Grupo cid10Grupo;

	//bi-directional many-to-one association to Cid10Subcateg
	@ManyToOne
	@JoinColumn(name="cd_subcateg")
	private Cid10Subcateg cid10Subcateg;

	//bi-directional many-to-one association to CidOCateg
	@ManyToOne
	@JoinColumn(name="cd_cid_o_categ")
	private CidOCateg cidOCateg;

	//bi-directional many-to-one association to CidOGrupo
	@ManyToOne
	@JoinColumn(name="cd_cid_o_grupo")
	private CidOGrupo cidOGrupo;

	public Doenca() {
	}

	public Integer getCdDoenca() {
		return this.cdDoenca;
	}

	public void setCdDoenca(Integer cdDoenca) {
		this.cdDoenca = cdDoenca;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<AtendDoenca> getAtendDoencas1() {
		return this.atendDoencas1;
	}

	public void setAtendDoencas1(List<AtendDoenca> atendDoencas1) {
		this.atendDoencas1 = atendDoencas1;
	}

	public AtendDoenca addAtendDoencas1(AtendDoenca atendDoencas1) {
		getAtendDoencas1().add(atendDoencas1);
		atendDoencas1.setDoenca1(this);

		return atendDoencas1;
	}

	public AtendDoenca removeAtendDoencas1(AtendDoenca atendDoencas1) {
		getAtendDoencas1().remove(atendDoencas1);
		atendDoencas1.setDoenca1(null);

		return atendDoencas1;
	}

	public List<AtendDoenca> getAtendDoencas2() {
		return this.atendDoencas2;
	}

	public void setAtendDoencas2(List<AtendDoenca> atendDoencas2) {
		this.atendDoencas2 = atendDoencas2;
	}

	public AtendDoenca addAtendDoencas2(AtendDoenca atendDoencas2) {
		getAtendDoencas2().add(atendDoencas2);
		atendDoencas2.setDoenca2(this);

		return atendDoencas2;
	}

	public AtendDoenca removeAtendDoencas2(AtendDoenca atendDoencas2) {
		getAtendDoencas2().remove(atendDoencas2);
		atendDoencas2.setDoenca2(null);

		return atendDoencas2;
	}

	public List<Atendimento> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Cid10Capitulo getCid10Capitulo() {
		return this.cid10Capitulo;
	}

	public void setCid10Capitulo(Cid10Capitulo cid10Capitulo) {
		this.cid10Capitulo = cid10Capitulo;
	}

	public Cid10Categ getCid10Categ() {
		return this.cid10Categ;
	}

	public void setCid10Categ(Cid10Categ cid10Categ) {
		this.cid10Categ = cid10Categ;
	}

	public Cid10Grupo getCid10Grupo() {
		return this.cid10Grupo;
	}

	public void setCid10Grupo(Cid10Grupo cid10Grupo) {
		this.cid10Grupo = cid10Grupo;
	}

	public Cid10Subcateg getCid10Subcateg() {
		return this.cid10Subcateg;
	}

	public void setCid10Subcateg(Cid10Subcateg cid10Subcateg) {
		this.cid10Subcateg = cid10Subcateg;
	}

	public CidOCateg getCidOCateg() {
		return this.cidOCateg;
	}

	public void setCidOCateg(CidOCateg cidOCateg) {
		this.cidOCateg = cidOCateg;
	}

	public CidOGrupo getCidOGrupo() {
		return this.cidOGrupo;
	}

	public void setCidOGrupo(CidOGrupo cidOGrupo) {
		this.cidOGrupo = cidOGrupo;
	}

}